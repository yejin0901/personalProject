package com.yjyj.ecommerce.payment.intrastructure.out.mq.config;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
@ConfigurationProperties(prefix = "spring.kafka")
@Setter
@Getter
public class KafkaConsumerProperties {
    private String[] bootstrapServers;
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;
    private String schemaRegistryUrl;

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {

      if (bootstrapServers == null || bootstrapServers.length == 0) {
        throw new IllegalStateException("bootstrapServers is null/empty. Check spring.kafka.bootstrap-servers binding.");
      }
      if (groupId == null || groupId.isBlank()) {
        throw new IllegalStateException("groupId is null/blank. Check spring.kafka.consumer.group-id binding.");
      }

        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, String.join(",", bootstrapServers));
        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        config.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8085");
        config.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
        config.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, 52_428_800);


        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}
