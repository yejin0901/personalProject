package com.yjyj.ecommerce.payment.intrastructure.out.repository.settlements;

import com.yjyj.ecommerce.payment.domain.settlements.PaymentSettlements;
import java.sql.PreparedStatement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PaymentSettlementsRepository implements com.yjyj.ecommerce.payment.application.port.out.repository.PaymentSettlementsRepository {
    private final JdbcTemplate jdbcTemplate;
    private final JpaPaymentSettlementsRepository jpaPaymentSettlementsRepository;

    @Override
    public PaymentSettlements findById(String paymentKey) {
        return jpaPaymentSettlementsRepository.findByPaymentKey(paymentKey)
                .orElseThrow(() -> new NullPointerException("Not Found PaymentSettlements with key: " + paymentKey));
    }

    @Override
    public void bulkInsert(List<PaymentSettlements> paymentSettlements) {
        String sqlStatement = "INSERT INTO payment_settlements (payment_id, method, settlements_status, total_amount, pay_out_amount, canceled_amount, sold_date, paid_out_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(
                sqlStatement,
                paymentSettlements,
                paymentSettlements.size(),
                (PreparedStatement ps, PaymentSettlements data) -> {
                    String paymentMethodName = data.getMethod().getMethodName();
                    log.info("data.getMethod().getMethodName() => {}",paymentMethodName);
                    ps.setString(1, data.getPaymentKey());
                    ps.setString(2, paymentMethodName);
                    ps.setString(3, data.getPaymentStatus().toString());
                    ps.setInt(4, data.getTotalAmount());
                    ps.setInt(5, data.getPayOutAmount());
                    ps.setInt(6, data.getCanceledAmount());
                    ps.setDate(7, data.getSoldDate());
                    ps.setDate(8, data.getPaidOutDate());
                });
    }
}
