package com.yjyj.ecommerce.payment.intrastructure.out.pg.toss.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yjyj.ecommerce.payment.domain.payment.PaymentLedger;
import com.yjyj.ecommerce.payment.domain.payment.PaymentMethod;
import com.yjyj.ecommerce.payment.domain.payment.PaymentStatus;
import com.yjyj.ecommerce.payment.intrastructure.out.pg.toss.response.payment.ResponsePaymentCommon;
import com.yjyj.ecommerce.payment.intrastructure.out.pg.toss.response.payment.method.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsePaymentApproved extends ResponsePaymentCommon {
    private String orderName;
    private Card card;
    private String lastTransactionKey;
    private int suppliedAmount; // 공급 가액
    private int vat;
    private String requestedAt; // 2024-06-18T15:13:15+09:00
    private String approvedAt;

    public PaymentLedger toPaymentTransactionEntity() {
        return PaymentLedger.builder()
            .paymentKey(this.getPaymentKey())
            .method(PaymentMethod.fromMethodName(this.getMethod()))
            .paymentStatus(PaymentStatus.valueOf(this.getStatus()))
            .totalAmount(this.getTotalAmount())
            .balanceAmount(this.getBalanceAmount())
            .canceledAmount(0)
            .build();
    }
}
