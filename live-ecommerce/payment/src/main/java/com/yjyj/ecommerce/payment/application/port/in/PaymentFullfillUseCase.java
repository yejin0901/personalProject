package com.yjyj.ecommerce.payment.application.port.in;


import com.yjyj.ecommerce.payment.representation.request.payment.PaymentApproved;
import java.io.IOException;

public interface PaymentFullfillUseCase {
    String paymentApproved(PaymentApproved paymentInfo) throws IOException;
}
