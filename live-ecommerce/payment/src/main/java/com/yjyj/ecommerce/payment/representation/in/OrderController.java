package com.yjyj.ecommerce.payment.representation.in;

import com.yjyj.ecommerce.payment.application.port.in.CreateNewOrderUseCase;
import com.yjyj.ecommerce.payment.application.port.in.GetOrderInfoUseCase;
import com.yjyj.ecommerce.payment.representation.request.order.PurchaseOrder;
import com.yjyj.ecommerce.payment.representation.response.NewPurchaseOrder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Tag(name = "주문 API")
public class OrderController {
    private final CreateNewOrderUseCase createNewOrderUseCase;
    private final GetOrderInfoUseCase getOrderInfoUseCase;

    @PostMapping("/new")
    @Operation(summary = "새 주문 생성")
    public NewPurchaseOrder newOrder(@RequestBody @Valid PurchaseOrder newOrder) throws Exception {
        return NewPurchaseOrder.from(createNewOrderUseCase.createOrder(newOrder));
    }

    @GetMapping
    public String test() throws Exception {
        return "test";
    }

    @GetMapping("info")
    public  Map<String, String> requestParams(@RequestParam(value = "username") String username){
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        return params;
    }

    @GetMapping("query")
    @Operation(summary = "주문 id로 조회")
    public NewPurchaseOrder getOrderById(@RequestParam(value = "order_id") UUID orderId){
        return NewPurchaseOrder.from(getOrderInfoUseCase.getOrderInfo(orderId));
    }

}
