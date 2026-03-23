package com.yjyj.ecommerce.payment.intrastructure.out.repository.order;

import com.yjyj.ecommerce.payment.domain.order.Order;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepository implements com.yjyj.ecommerce.payment.application.port.out.repository.OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;

    @Override
    public Order findById(UUID id) {
        return jpaOrderRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("OrderId not found"));
    }

    @Override
    public Order save(Order newOrder) {
        return jpaOrderRepository.save(newOrder);
    }

    @Override
    public boolean removeAll(UUID id) {
        return jpaOrderRepository.deleteById(id);
    }

}
