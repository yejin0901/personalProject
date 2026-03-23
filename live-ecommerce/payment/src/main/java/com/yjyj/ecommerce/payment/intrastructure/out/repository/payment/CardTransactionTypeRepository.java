package com.yjyj.ecommerce.payment.intrastructure.out.repository.payment;

import com.yjyj.ecommerce.payment.application.port.out.repository.TransactionTypeRepository;
import com.yjyj.ecommerce.payment.domain.payment.TransactionType;
import com.yjyj.ecommerce.payment.domain.payment.card.CardPayment;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CardTransactionTypeRepository implements TransactionTypeRepository {
    private final JpaCardPaymentRepository jpaCardPaymentRepository;

    @Override
    public CardPayment findById(String paymentKey) {
        return jpaCardPaymentRepository.findById(paymentKey)
            .orElseThrow(() -> new NoSuchElementException(String.format("CardPayment with key '%s' not found", paymentKey)));
    }

    @Override
    public void save(TransactionType paymentDetailInfo) {
        jpaCardPaymentRepository.save((CardPayment) paymentDetailInfo);
    }
}
