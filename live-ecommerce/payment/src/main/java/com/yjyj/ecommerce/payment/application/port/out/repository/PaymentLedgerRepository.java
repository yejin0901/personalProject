package com.yjyj.ecommerce.payment.application.port.out.repository;


import com.yjyj.ecommerce.payment.domain.payment.PaymentLedger;
import java.util.List;

public interface PaymentLedgerRepository {
    List<PaymentLedger> findAllByPaymentKey(String paymentKey);
    PaymentLedger findOneByPaymentKeyDesc(String paymentKey);
    void save(PaymentLedger paymentLedgerInfo);
    void bulkInsert(List<PaymentLedger> paymentLedgerHistories);
}
