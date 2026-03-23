package com.yjyj.ecommerce.payment.application.port.out.repository;

import com.yjyj.ecommerce.payment.domain.settlements.PaymentSettlements;
import java.util.List;

public interface PaymentSettlementsRepository {
    PaymentSettlements findById(String paymentKey);
    void bulkInsert(List<PaymentSettlements> paymentSettlements);
}
