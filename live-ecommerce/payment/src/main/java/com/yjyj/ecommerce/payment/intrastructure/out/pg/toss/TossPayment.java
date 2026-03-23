package com.yjyj.ecommerce.payment.intrastructure.out.pg.toss;

import com.yjyj.ecommerce.payment.application.port.out.api.PaymentAPIs;
import com.yjyj.ecommerce.payment.intrastructure.out.pg.toss.response.ResponsePaymentApproved;
import com.yjyj.ecommerce.payment.intrastructure.out.pg.toss.response.ResponsePaymentCancel;
import com.yjyj.ecommerce.payment.intrastructure.out.pg.toss.response.ResponsePaymentSettlements;
import com.yjyj.ecommerce.payment.representation.request.payment.PaymentApproved;
import com.yjyj.ecommerce.payment.representation.request.payment.PaymentCancel;
import com.yjyj.ecommerce.payment.representation.request.payment.PaymentSettlement;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import retrofit2.Response;

@Component
@RequiredArgsConstructor
public class TossPayment implements PaymentAPIs {
    private final TossPaymentAPIs tossClient;

    @Override
    public ResponsePaymentApproved requestPaymentApprove(PaymentApproved paymentInfo) throws IOException {
        Response<ResponsePaymentApproved> response = tossClient.paymentFullfill(paymentInfo).execute();
        if (response.isSuccessful()) {
            return response.body();
        }

        throw new IOException(response.message());
    }

    @Override
    public boolean isPaymentApproved(String status) {
        return "DONE".equalsIgnoreCase(status);
    }

    @Override
    public ResponsePaymentCancel requestPaymentCancel(String paymentKey, PaymentCancel cancelMessage) throws IOException {
        Response<ResponsePaymentCancel> response = tossClient.paymentCancel(paymentKey, cancelMessage).execute();
        if (response.isSuccessful()) {
            return response.body();
        }

        throw new IOException(response.message());
    }

    @Override
    public List<ResponsePaymentSettlements> requestPaymentSettlement(
        PaymentSettlement paymentSettlement) throws IOException {
        String startDate = paymentSettlement.getStartDate();
        String endDate = paymentSettlement.getEndDate();
        int page = paymentSettlement.getPage();
        int size = paymentSettlement.getSize();

        Response<List<ResponsePaymentSettlements>> response = tossClient.paymentSettlements(startDate, endDate, page, size).execute();
        if(response.isSuccessful() && response.body() != null && !response.body().isEmpty())  {
            return response.body();
        }

        throw new IOException(response.message());
    }
}
