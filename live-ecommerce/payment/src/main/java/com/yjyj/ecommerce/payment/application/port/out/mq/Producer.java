package com.yjyj.ecommerce.payment.application.port.out.mq;

public interface Producer<T> {
    boolean send(String topic, T record);
}
