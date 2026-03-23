package com.yjyj.ecommerce.payment.representation.in;

import com.yjyj.ecommerce.payment.application.port.in.PaymentSettlementsUseCase;
import com.yjyj.ecommerce.payment.application.port.in.SendSettlementsInfoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/settlements")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "결제 API")
public class SettlementsController {
    private final PaymentSettlementsUseCase paymentSettlementsUseCase;
    private final SendSettlementsInfoUseCase sendSettlementsInfoUseCase;

    @GetMapping
    @Operation(summary = "정산 조회")
    public boolean fetchSettlements() throws Exception {
        paymentSettlementsUseCase.getPaymentSettlements();
        return true;
    }

    @GetMapping("/produce")
    @Operation(summary = "정산 이벹트 발행")
    public boolean produceSettlements() throws Exception {
        sendSettlementsInfoUseCase.send();
        return true;
    }
}
