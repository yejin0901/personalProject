package com.yjyj.ecommerce.payment.intrastructure.out.repository.payment;


import com.yjyj.ecommerce.payment.domain.payment.card.CardPayment;
import com.yjyj.ecommerce.payment.intrastructure.out.repository.JpaBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCardPaymentRepository extends JpaBaseRepository<CardPayment, String> {
}