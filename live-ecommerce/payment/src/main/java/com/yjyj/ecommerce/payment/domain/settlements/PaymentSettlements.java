package com.yjyj.ecommerce.payment.domain.settlements;

import com.yjyj.ecommerce.payment.domain.payment.PaymentLedger;
import com.yjyj.ecommerce.payment.domain.payment.PaymentMethod;
import com.yjyj.ecommerce.payment.domain.payment.PaymentMethodConverter;
import com.yjyj.ecommerce.payment.domain.payment.PaymentStatus;
import com.yjyj.ecommerce.payment.domain.payment.PaymentStatusConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "payment_settlements")
@Getter
@AllArgsConstructor
@Builder
public class PaymentSettlements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "payment_id")
    private String paymentKey; // example) tgen_20240605132741Jtkz1

    @Convert(converter = PaymentMethodConverter.class)
    private PaymentMethod method; // CARD:카드

    @Column(name = "settlements_status")
    @Convert(converter = PaymentStatusConverter.class)
    private PaymentStatus paymentStatus; // DONE, CANCELED, PARTIAL_CANCELED, SETTLEMENTS_REQUESTED, SETTLEMENTS_COMPLETED

    @Column(name = "total_amount")
    private int totalAmount;

    @Column(name = "pay_out_amount")
    private int payOutAmount;

    @Column(name = "canceled_amount")
    private int canceledAmount;

    @Column(name = "sold_date")
    private Date soldDate;

    @Column(name = "paid_out_date")
    private Date paidOutDate;

    protected PaymentSettlements() {
    }

    public PaymentLedger toPaymentLedger() {
        return PaymentLedger.builder()
            .paymentKey(paymentKey)
            .method(method)
            .paymentStatus(paymentStatus)
            .totalAmount(totalAmount)
            .balanceAmount(totalAmount - canceledAmount)
            .canceledAmount(canceledAmount)
            .payOutAmount(payOutAmount)
            .build();
    }

}
