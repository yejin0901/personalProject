package com.yjyj.ecommerce.payment.application.service;

import com.yjyj.ecommerce.payment.application.port.in.GetPaymentInfoUseCase;
import com.yjyj.ecommerce.payment.application.port.in.PaymentFullfillUseCase;
import com.yjyj.ecommerce.payment.application.port.out.api.PaymentAPIs;
import com.yjyj.ecommerce.payment.application.port.out.repository.OrderRepository;
import com.yjyj.ecommerce.payment.application.port.out.repository.PaymentLedgerRepository;
import com.yjyj.ecommerce.payment.application.port.out.repository.TransactionTypeRepository;
import com.yjyj.ecommerce.payment.domain.order.Order;
import com.yjyj.ecommerce.payment.domain.order.OrderStatus;
import com.yjyj.ecommerce.payment.domain.payment.PaymentLedger;
import com.yjyj.ecommerce.payment.domain.payment.PaymentMethod;
import com.yjyj.ecommerce.payment.domain.payment.TransactionType;
import com.yjyj.ecommerce.payment.intrastructure.out.pg.toss.response.ResponsePaymentApproved;
import com.yjyj.ecommerce.payment.representation.request.payment.PaymentApproved;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService implements PaymentFullfillUseCase, GetPaymentInfoUseCase  {
    private final PaymentAPIs tossPayment;
    private final OrderRepository orderRepository;
    private final PaymentLedgerRepository paymentLedgerRepository;
    private final Set<TransactionTypeRepository> transactionTypeRepositorySet;

    private final Map<String, TransactionTypeRepository> transactionTypeRepositories = new HashMap<>();
    private TransactionTypeRepository transactionTypeRepository;

    @PostConstruct
    public void init() {
        for (TransactionTypeRepository transactionTypeRepository : transactionTypeRepositorySet) {
            String paymentMethodType = transactionTypeRepository.getClass().getSimpleName().split("TransactionTypeRepository")[0].toLowerCase();
            transactionTypeRepositories.put(paymentMethodType, transactionTypeRepository);
        }
    }

    @Transactional
    @Override
    public String paymentApproved(PaymentApproved paymentInfo) throws IOException {
        verifyOrderIsCompleted(UUID.fromString(paymentInfo.getOrderId()));
        ResponsePaymentApproved response = tossPayment.requestPaymentApprove(paymentInfo);

        if (tossPayment.isPaymentApproved(response.getStatus())) {
            Order completedOrder = orderRepository.findById(UUID.fromString(response.getOrderId()));
            completedOrder.orderPaymentFullFill(response.getPaymentKey());
            paymentLedgerRepository.save(response.toPaymentTransactionEntity());
            PaymentMethod method = PaymentMethod.fromMethodName(response.getMethod());
            initTransactionTypeRepository(method);
            transactionTypeRepository.save(TransactionType.convertToTransactionType(response));

            return "success";
        }

        return "fail";
    }

    @Override
    public List<PaymentLedger> getPaymentInfo(String paymentKey) {
        return paymentLedgerRepository.findAllByPaymentKey(paymentKey);
    }

    @Override
    public PaymentLedger getLatestPaymentInfoOnlyOne(String paymentKey) {
        return paymentLedgerRepository.findOneByPaymentKeyDesc(paymentKey);
    }



    public void verifyOrderIsCompleted(UUID orderId) throws IllegalArgumentException {
        OrderStatus status = orderRepository.findById(orderId).getStatus();
        if (!status.equals(OrderStatus.ORDER_COMPLETED))
            throw new IllegalArgumentException("Order is not completed || Order is already paymented");
    }

    private void initTransactionTypeRepository(PaymentMethod paymentMethod) {
        switch (paymentMethod.toString().toLowerCase()) {
            case "card" -> {
                transactionTypeRepository = transactionTypeRepositories.get("card");
            }
            default -> throw new RuntimeException("Unsupported payment method: " + paymentMethod);
        }
    }

}
