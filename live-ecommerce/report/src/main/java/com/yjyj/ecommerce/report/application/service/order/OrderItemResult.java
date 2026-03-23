package com.yjyj.ecommerce.report.application.service.order;

import com.yjyj.ecommerce.report.domain.order.OrderItem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderItemResult {

  private Long orderItemId;
  private Integer quantity;
  private Integer unitPrice;
  private String productId;

  public static OrderItemResult from(OrderItem orderItem) {
    return new OrderItemResult(orderItem.getOrderItemId(),
        orderItem.getQuantity(), orderItem.getUnitPrice(), orderItem.getProductId());
  }
}
