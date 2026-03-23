package com.yjyj.ecommerce.report.application.service.order;


import com.yjyj.ecommerce.report.application.service.product.ProductResult;
import com.yjyj.ecommerce.report.application.service.product.ProductStockService;
import com.yjyj.ecommerce.report.domain.order.Order;
import com.yjyj.ecommerce.report.domain.order.OrderItem;
import com.yjyj.ecommerce.report.domain.order.OrderReportRepository;
import com.yjyj.ecommerce.report.domain.payment.PaymentMethod;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderReportService {

  private final OrderReportRepository orderReportRepository;
  private final ProductStockService productStockService;

  @Transactional
  public OrderResult order(Long customerId, List<OrderItemCommand> orderItems,
      PaymentMethod paymentMethod) {
    Order order = Order.createOrder(customerId);
    for (OrderItemCommand item : orderItems) {
      ProductResult product = productStockService.findProduct(item.getProductId());
      order.addOrderItem(product.getProductId(), item.getQuantity(),
          product.getSalesPrice());
    }
    order.initPayment(paymentMethod);
    return save(order);
  }

  private OrderResult save(Order order) {
    return OrderResult.from(orderReportRepository.save(order));
  }


  @Transactional
  public OrderResult completePayment(Long orderId, boolean isSuccess) {
    Order order = orderReportRepository.findById(orderId)
        .orElseThrow(() -> new OrderNotFoundException(orderId));
    order.completePayment(isSuccess);
    decreaseStock(isSuccess, order);
    return save(order);
  }

  private void decreaseStock(boolean isSuccess, Order order) {
    if (isSuccess) {
      for (OrderItem orderItem : order.getOrderItems()) {
        productStockService.decreaseStock(orderItem.getProductId(), orderItem.getQuantity());
      }
    }
  }


  @Transactional
  public OrderResult completeOrder(Long orderId) {
    Order order = orderReportRepository.findById(orderId)
        .orElseThrow(() -> new OrderNotFoundException(orderId));
    order.completeOrder();
    return save(order);
  }

  @Transactional
  public OrderResult cancelOrder(Long orderId) {
    Order order = orderReportRepository.findById(orderId)
        .orElseThrow(() -> new OrderNotFoundException(orderId));
    order.cancel();
    recoverStock(order);
    return save(order);
  }

  private void recoverStock(Order order) {
    for (OrderItem orderItem : order.getOrderItems()) {
      productStockService.increaseStock(orderItem.getProductId(), orderItem.getQuantity());
    }
  }
}