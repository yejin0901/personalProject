package com.yjyj.ecommerce.payment.representation.in;

import com.yjyj.ecommerce.payment.application.port.in.PaymentCancelUseCase;
import com.yjyj.ecommerce.payment.representation.request.order.CancelOrder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Tag(name = "결제 API")
public class CancelController {
    private final PaymentCancelUseCase paymentCancelUseCase;

    @PostMapping("/cancel")
    @Operation(summary = "결제 취소")
    public boolean cancelPayment(@RequestBody @Valid CancelOrder cancelOrder) throws Exception {
        return paymentCancelUseCase.paymentCancel(cancelOrder);
    }

}
