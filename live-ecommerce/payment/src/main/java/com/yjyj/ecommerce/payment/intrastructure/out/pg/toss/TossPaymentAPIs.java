package com.yjyj.ecommerce.payment.intrastructure.out.pg.toss;

import com.yjyj.ecommerce.payment.intrastructure.out.pg.toss.response.ResponsePaymentApproved;
import com.yjyj.ecommerce.payment.intrastructure.out.pg.toss.response.ResponsePaymentCancel;
import com.yjyj.ecommerce.payment.intrastructure.out.pg.toss.response.ResponsePaymentSettlements;
import com.yjyj.ecommerce.payment.representation.request.payment.PaymentApproved;
import com.yjyj.ecommerce.payment.representation.request.payment.PaymentCancel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


@Tag(name = "결제 API")
public interface TossPaymentAPIs {
    @POST("payments/confirm")
    @Operation(summary = "결제 승인")
    Call<ResponsePaymentApproved> paymentFullfill(@Body PaymentApproved requestMessage);

    @POST("payments/{paymentKey}/cancel")
    @Operation(summary = "결제 취소")
    Call<ResponsePaymentCancel> paymentCancel(@Path("paymentKey") String paymentKey, @Body PaymentCancel requestMessage);

    @GET("settlements")
    @Operation(summary = "결제 정산")
    Call<List<ResponsePaymentSettlements>> paymentSettlements(@Path("startDate") String startDate,
                                                              @Path("endDate") String endDate,
                                                              @Path("page") int page,
                                                              @Path("size") int size);
}