package com.yjyj.ecommerce.payment.intrastructure.out.pg.mock;

import com.yjyj.ecommerce.payment.intrastructure.out.pg.toss.response.ResponsePaymentSettlements;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

@Tag(name = "결제 API")
public interface MockTossPaymentAPIs {
    @GET("settlements")
    @Operation(summary = "정산 처리")
    Call<List<ResponsePaymentSettlements>> paymentSettlements();
}