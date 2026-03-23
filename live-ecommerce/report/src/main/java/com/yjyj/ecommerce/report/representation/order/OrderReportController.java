package com.yjyj.ecommerce.report.representation.order;

import com.yjyj.ecommerce.report.application.service.order.OrderReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 주문 관련 API 컨트롤러
 */
@RestController
@RequestMapping("/v1/orders")
@Tag(name = "주문 (보고서) API")
public class OrderReportController {

  @Autowired
  private OrderReportService orderReportService;

  @PostMapping
  @Operation(summary = "주문 생성")
  public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
    return OrderResponse.from(orderReportService.order(orderRequest.getCustomerId(),
        orderRequest.toOrderItemCommands(), orderRequest.getPaymentMethod()));
  }

  @PostMapping("/{orderId}/payment")
  @Operation(summary = "결제 완료")
  public OrderResponse completePayment(@PathVariable Long orderId,
      @RequestBody PaymentRequest paymentRequest) {
    return OrderResponse.from(orderReportService.completePayment(orderId, paymentRequest.isSuccess()));
  }

  @PostMapping("/{orderId}/complete")
  @Operation(summary = "주문 완료")
  public OrderResponse completeOrder(@PathVariable Long orderId) {
    return OrderResponse.from(orderReportService.completeOrder(orderId));
  }

  @PostMapping("/{orderId}/cancel")
  @Operation(summary = "주문 취소")
  public OrderResponse cancelOrder(@PathVariable Long orderId) {
    return OrderResponse.from(orderReportService.cancelOrder(orderId));
  }
}