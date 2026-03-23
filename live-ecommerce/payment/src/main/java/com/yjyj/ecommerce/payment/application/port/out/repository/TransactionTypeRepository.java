package com.yjyj.ecommerce.payment.application.port.out.repository;


import com.yjyj.ecommerce.payment.domain.payment.TransactionType;

public interface TransactionTypeRepository {
    TransactionType findById(String paymentKey);
    void save(TransactionType paymentDetailInfo);
}
