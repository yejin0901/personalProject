package com.yjyj.ecommerce.payment.application.port.in;


import com.yjyj.ecommerce.payment.domain.order.Order;
import java.util.UUID;

public interface GetOrderInfoUseCase {
    Order getOrderInfo(UUID orderId);
}
