package com.yjyj.ecommerce.payment.intrastructure.out.repository.payment;


import com.yjyj.ecommerce.payment.domain.payment.PaymentLedger;
import com.yjyj.ecommerce.payment.intrastructure.out.repository.JpaBaseRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPaymentLedgerRepository extends JpaBaseRepository<PaymentLedger, String> {
    Optional<List<PaymentLedger>> findByPaymentKey(String paymentKey);

    Optional<PaymentLedger> findTopByPaymentKeyOrderByIdDesc(String paymentKey);
}