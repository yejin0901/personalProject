package com.yjyj.ecommerce.payment.application.port.out.repository;


import com.yjyj.ecommerce.payment.domain.order.Order;
import java.util.UUID;

public interface OrderRepository {
    Order findById(UUID id);
    Order save(Order newOrder);
    boolean removeAll(UUID id);
}
