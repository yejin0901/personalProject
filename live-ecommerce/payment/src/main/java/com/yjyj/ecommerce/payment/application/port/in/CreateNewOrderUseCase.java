package com.yjyj.ecommerce.payment.application.port.in;


import com.yjyj.ecommerce.payment.domain.order.Order;
import com.yjyj.ecommerce.payment.representation.request.order.PurchaseOrder;

public interface CreateNewOrderUseCase {
    Order createOrder(PurchaseOrder newOrder) throws Exception;
}
