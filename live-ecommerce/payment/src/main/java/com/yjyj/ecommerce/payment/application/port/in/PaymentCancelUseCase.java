package com.yjyj.ecommerce.payment.application.port.in;


import com.yjyj.ecommerce.payment.representation.request.order.CancelOrder;

public interface PaymentCancelUseCase {
    boolean paymentCancel(CancelOrder cancelOrder) throws Exception;
}
