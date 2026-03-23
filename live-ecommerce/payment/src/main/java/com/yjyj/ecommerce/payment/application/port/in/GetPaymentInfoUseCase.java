package com.yjyj.ecommerce.payment.application.port.in;


import com.yjyj.ecommerce.payment.domain.payment.PaymentLedger;
import java.util.List;

public interface GetPaymentInfoUseCase {
    List<PaymentLedger> getPaymentInfo(String paymentKey);
    PaymentLedger getLatestPaymentInfoOnlyOne(String paymentKey);
}
