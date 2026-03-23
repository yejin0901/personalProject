package com.yjyj.ecommerce.payment.representation.response;

import com.yjyj.ecommerce.payment.domain.order.Order;
import com.yjyj.ecommerce.payment.domain.order.OrderItem;
import com.yjyj.ecommerce.payment.domain.order.OrderStatus;
import com.yjyj.ecommerce.payment.representation.request.order.Orderer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
@Getter
@Slf4j
public class NewPurchaseOrder {
    private final UUID orderId;

    private final Orderer orderer;

    private final String paymentId;

    private final int totalPrice;

    private final OrderStatus status;

    @Getter
    private List<NewPurchaseOrderItem> items = new ArrayList<>();

    private NewPurchaseOrder(UUID id, String name, String phoneNumber, String paymentId, int totalPrice, OrderStatus status, List<OrderItem> items) {
        this.orderId = id;
        this.orderer = new Orderer(name, phoneNumber);
        this.paymentId = paymentId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.items = NewPurchaseOrderItem.from(items);
    }

    public static NewPurchaseOrder from(Order order) {
        log.info("orderItems -> {}", order.getItems());
        return new NewPurchaseOrder(order.getOrderId(), order.getName(), order.getPhoneNumber(), order.getPaymentId(), order.getTotalPrice(),
                order.getStatus(), order.getItems());
    }
}
