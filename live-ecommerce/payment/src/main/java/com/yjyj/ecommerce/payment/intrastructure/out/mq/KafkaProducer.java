package com.yjyj.ecommerce.payment.intrastructure.out.mq;

import com.yjyj.ecommerce.payment.application.port.out.mq.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer<T> implements Producer<T> {
    private final KafkaTemplate<String, T> kafkaTemplate;

    @Override
    public boolean send(String topic, T record) {
        log.info("sending payloa={} to topic={}", record, topic);
        kafkaTemplate.send(topic, record);
        return false;
    }
}
