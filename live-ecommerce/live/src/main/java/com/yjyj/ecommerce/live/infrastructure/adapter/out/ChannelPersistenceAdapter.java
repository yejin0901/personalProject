package com.yjyj.ecommerce.live.infrastructure.adapter.out;


import com.yjyj.ecommerce.live.application.port.out.LoadChannelPort;
import com.yjyj.ecommerce.live.application.port.out.SaveChannelPort;
import com.yjyj.ecommerce.live.domain.channel.Channel;
import com.yjyj.ecommerce.live.infrastructure.adapter.out.jpa.channel.ChannelJpaEntity;
import com.yjyj.ecommerce.live.infrastructure.adapter.out.jpa.channel.ChannelJpaRepository;
import com.yjyj.ecommerce.live.infrastructure.adapter.out.redis.channel.ChannelRedisHash;
import com.yjyj.ecommerce.live.infrastructure.adapter.out.redis.channel.ChannelRedisRepository;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class ChannelPersistenceAdapter implements LoadChannelPort, SaveChannelPort {
    private final ChannelJpaRepository channelJpaRepository;
    private final ChannelRedisRepository channelRedisRepository;

    public ChannelPersistenceAdapter(ChannelJpaRepository channelJpaRepository, ChannelRedisRepository channelRedisRepository) {
        this.channelJpaRepository = channelJpaRepository;
        this.channelRedisRepository = channelRedisRepository;
    }

    @Override
    public void saveChannel(Channel channel) {
        channelRedisRepository.deleteById(channel.getId());
        channelRedisRepository.save(ChannelRedisHash.from(channel));

        channelJpaRepository.save(ChannelJpaEntity.from(channel));
    }

    @Override
    public Optional<Channel> loadChannel(String id) {
        return channelRedisRepository.findById(id)
                // redis cache hit
                .map(ChannelRedisHash::toDomain)
                .or(() -> {
                    // redis cache miss
                    var optionalEntity = channelJpaRepository.findById(id);
                    optionalEntity.ifPresent(jpaEntity -> channelRedisRepository.save(ChannelRedisHash.from(jpaEntity.toDomain())));

                    return optionalEntity.map(ChannelJpaEntity::toDomain);
                });
    }
}
