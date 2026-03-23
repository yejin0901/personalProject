package com.yjyj.ecommerce.payment.application.service;

import com.yjyj.ecommerce.payment.application.port.in.PaymentCancelUseCase;
import com.yjyj.ecommerce.payment.application.port.out.api.PaymentAPIs;
import com.yjyj.ecommerce.payment.application.port.out.repository.PaymentLedgerRepository;
import com.yjyj.ecommerce.payment.domain.order.Order;
import com.yjyj.ecommerce.payment.domain.payment.PaymentLedger;
import com.yjyj.ecommerce.payment.intrastructure.out.pg.toss.response.ResponsePaymentCancel;
import com.yjyj.ecommerce.payment.representation.request.order.CancelOrder;
import com.yjyj.ecommerce.payment.representation.request.payment.PaymentCancel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class CancelService implements PaymentCancelUseCase {
    private final PaymentAPIs tossPayment;
    private final OrderService orderService;
    private final PaymentService paymentService;
    private final PaymentLedgerRepository paymentLedgerRepository;

    @Transactional
    @Override
    public boolean paymentCancel(CancelOrder cancelOrder) throws Exception {
        String paymentKey = cancelOrder.getPaymentKey();
        int cancellationAmount = cancelOrder.getCancellationAmount();
        Order wantedCancelOrder = orderService.getOrderInfo(cancelOrder.getOrderId());
        PaymentLedger paymentInfo = paymentService.getLatestPaymentInfoOnlyOne(paymentKey);
        if (wantedCancelOrder.isNotOrderStatusPurchaseDecision() &&
            paymentInfo.isCancellableAmountGreaterThan(cancellationAmount)) {
            ResponsePaymentCancel response = tossPayment.requestPaymentCancel(paymentKey, new PaymentCancel(cancelOrder.getCancelReason(), cancellationAmount));
            paymentLedgerRepository.save(response.toEntity());

            if (cancelOrder.hasItemIdx())
                wantedCancelOrder.orderCancel(cancelOrder.getItemIdxs());
            else
                wantedCancelOrder.orderAllCancel();
            return true;
        }

        throw new Exception("Not Enough CancellationAmount");
    }
}
