package com.yjyj.ecommerce.live.infrastructure.adapter.out;

import static com.yjyj.ecommerce.common.common.CacheNames.VIDEO;
import static com.yjyj.ecommerce.common.common.CacheNames.VIDEO_LIST;
import static com.yjyj.ecommerce.common.common.RedisKeyGenerator.getVideoViewCountKey;

import com.yjyj.ecommerce.common.common.RedisKeyGenerator;
import com.yjyj.ecommerce.live.application.port.out.LoadVideoPort;
import com.yjyj.ecommerce.live.application.port.out.SaveVideoPort;
import com.yjyj.ecommerce.live.domain.video.Video;
import com.yjyj.ecommerce.live.infrastructure.adapter.out.jpa.video.VideoJpaEntity;
import com.yjyj.ecommerce.live.infrastructure.adapter.out.jpa.video.VideoJpaRepository;
import java.util.Collections;
import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class VideoPersistenceAdapter implements LoadVideoPort, SaveVideoPort {
    private final VideoJpaRepository videoJpaRepository;
    private final RedisTemplate<String, Long> redisTemplate;
    private final StringRedisTemplate stringRedisTemplate;

    public VideoPersistenceAdapter(VideoJpaRepository videoJpaRepository, RedisTemplate<String, Long> redisTemplate, StringRedisTemplate stringRedisTemplate) {
        this.videoJpaRepository = videoJpaRepository;
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    @Cacheable(cacheNames = VIDEO, key = "#videoId")
    public Video loadVideo(String videoId) {
        return videoJpaRepository.findById(videoId)
            .map(VideoJpaEntity::toDomain)
            .orElseThrow();
    }

    @Override
    @Cacheable(cacheNames = VIDEO_LIST, key = "#channelId")
    public List<Video> loadVideoByChannel(String channelId) {
        return videoJpaRepository.findByChannelId(channelId).stream()
            .map(VideoJpaEntity::toDomain)
            .toList();
    }

    @Override
    @Caching(evict = {
        @CacheEvict(cacheNames = VIDEO_LIST, key = "#video.channelId"),
        @CacheEvict(cacheNames = VIDEO, key = "#video.id")
    })
    public void saveVideo(Video video) {
        videoJpaRepository.save(VideoJpaEntity.from(video));
    }

    @Override
    public void incrementViewCount(String videoId) {
        var videoViewCountKey = getVideoViewCountKey(videoId);
        redisTemplate.opsForValue().increment(videoViewCountKey);

//        Using RedisAtomicLong
//        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(videoViewCountKey, redisTemplate.getConnectionFactory());
//        redisAtomicLong.incrementAndGet();

//        stringRedisTemplate.opsForSet().add(getVideoViewCountSetKey(), videoId);
    }

    @Override
    public Long getViewCount(String videoId) {
        var videoViewCountKey = getVideoViewCountKey(videoId);
        var viewCont = redisTemplate.opsForValue().get(videoViewCountKey);
        return viewCont == null ? 0 : viewCont;
    }

    @Override
    public List<String> getAllVideoIdsWithViewCount() {
        var members = stringRedisTemplate.opsForSet().members(RedisKeyGenerator.getVideoViewCountSetKey());
        if (members == null) {
            return Collections.emptyList();
        }

        return members.stream().toList();
    }

    @Override
    public void syncViewCount(String videoId) {
        videoJpaRepository.findById(videoId)
            .ifPresent(videoJpaEntity -> {
                // video:view-count:videoId
                var viewCount = redisTemplate.opsForValue().get(getVideoViewCountKey(videoId));
                videoJpaEntity.updateViewCount(redisTemplate.opsForValue().get(getVideoViewCountKey(videoId)));
                videoJpaRepository.save(videoJpaEntity);

                redisTemplate.opsForSet().remove(RedisKeyGenerator.getVideoViewCountSetKey(), videoId);
            });
    }
}