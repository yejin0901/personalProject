package com.yjyj.ecommerce.payment.application.service;

import com.yjyj.ecommerce.payment.application.port.in.CreateNewOrderUseCase;
import com.yjyj.ecommerce.payment.application.port.in.GetOrderInfoUseCase;
import com.yjyj.ecommerce.payment.application.port.out.repository.OrderRepository;
import com.yjyj.ecommerce.payment.domain.order.Order;
import com.yjyj.ecommerce.payment.representation.request.order.PurchaseOrder;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService implements CreateNewOrderUseCase, GetOrderInfoUseCase {
    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public Order createOrder(PurchaseOrder newOrder) throws Exception {
        return orderRepository.save(newOrder.toEntity());
    }

    @Transactional
    @Override
    public Order getOrderInfo(UUID orderId) {
        return orderRepository.findById(orderId);
    }

}
