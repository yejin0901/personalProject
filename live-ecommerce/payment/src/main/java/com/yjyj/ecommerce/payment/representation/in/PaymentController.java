package com.yjyj.ecommerce.payment.representation.in;

import com.yjyj.ecommerce.payment.application.port.in.PaymentFullfillUseCase;
import com.yjyj.ecommerce.payment.representation.request.payment.PaymentApproved;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
@Tag(name = "주문 상태 API")
public class PaymentController {
    private final PaymentFullfillUseCase paymentFullFillService;

    @GetMapping("/success")
    public String paymentFullfill(@RequestParam(value = "paymentType") String paymentType, @RequestParam(value = "orderId") String orderId,
                                  @RequestParam(value = "paymentKey") String paymentKey, @RequestParam(value = "amount") String amount
    ) {
        return "success";
    }

    @GetMapping("/fail")
    public String paymentFail(@RequestParam(value = "message") String message) {
        return "fail";
    }

    @PostMapping("/confirm")
    @Operation(summary = "주문 상태 확인")
    public String paymentConfirm(@RequestBody PaymentApproved paymentApproved) throws Exception {
        /*http://localhost:8080/payment/checkout.html?orderId=f8228ea2-915d-4967-aaa1-8c21e4aa8387&userId=fastcamp-y&ordererName=%EC%9C%A0%EC%A7%84%ED%98%B8&ordererPhoneNumber=01012341234&orderName=%EC%86%8D%EC%9D%B4%ED%8E%B8%ED%95%9C%EC%9A%B0%EC%9C%A0&amount=13400*/
        return paymentFullFillService.paymentApproved(paymentApproved);
    }

}
