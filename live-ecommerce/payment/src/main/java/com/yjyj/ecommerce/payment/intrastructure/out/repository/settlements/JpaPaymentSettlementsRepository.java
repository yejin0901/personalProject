package com.yjyj.ecommerce.payment.intrastructure.out.repository.settlements;

import com.yjyj.ecommerce.payment.domain.settlements.PaymentSettlements;
import com.yjyj.ecommerce.payment.intrastructure.out.repository.JpaBaseRepository;
import java.util.Optional;

public interface JpaPaymentSettlementsRepository extends
    JpaBaseRepository<PaymentSettlements, Integer> {
    Optional<PaymentSettlements> findByPaymentKey(String paymentKey);
}
