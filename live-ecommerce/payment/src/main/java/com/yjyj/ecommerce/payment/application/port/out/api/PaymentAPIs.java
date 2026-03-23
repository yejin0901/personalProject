package com.yjyj.ecommerce.payment.application.port.out.api;


import com.yjyj.ecommerce.payment.intrastructure.out.pg.toss.response.ResponsePaymentApproved;
import com.yjyj.ecommerce.payment.intrastructure.out.pg.toss.response.ResponsePaymentCancel;
import com.yjyj.ecommerce.payment.intrastructure.out.pg.toss.response.ResponsePaymentSettlements;
import com.yjyj.ecommerce.payment.representation.request.payment.PaymentApproved;
import com.yjyj.ecommerce.payment.representation.request.payment.PaymentCancel;
import com.yjyj.ecommerce.payment.representation.request.payment.PaymentSettlement;
import java.io.IOException;
import java.util.List;

public interface PaymentAPIs {
    ResponsePaymentApproved requestPaymentApprove(PaymentApproved requestMessage) throws IOException;
    boolean isPaymentApproved(String status);
    ResponsePaymentCancel requestPaymentCancel(String paymentKey, PaymentCancel cancelMessage) throws IOException;
    List<ResponsePaymentSettlements> requestPaymentSettlement(PaymentSettlement paymentSettlement) throws IOException;
}
