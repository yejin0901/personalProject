package com.yjyj.ecommerce.payment.representation.request.order;

import com.yjyj.ecommerce.payment.domain.order.Order;
import com.yjyj.ecommerce.payment.domain.order.OrderItem;
import com.yjyj.ecommerce.payment.domain.order.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {

    @NotNull(message = "The orderer is required.")
    @Valid
    private Orderer orderer;

    @Size(min = 1)
    @Valid
    private List<PurchaseOrderItem> newlyOrderedItem;

    public List<OrderItem> convert2OrderItems(Order o) {
        return newlyOrderedItem.stream()
                .map(items -> convert2OrderItem(items, o))
                .toList();
    }

    private OrderItem convert2OrderItem(PurchaseOrderItem item, Order o) {
        return OrderItem.builder()
                .order(o)
                .itemIdx(item.getItemIdx())
                .productId(item.getProductId())
                .productName(item.getProductName())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .size("FREE")
                .state(OrderStatus.ORDER_COMPLETED)
                .build();
    }

    public Order toEntity() throws Exception {
        Order o = Order.builder()
                .items(new ArrayList<>())
                .name(this.getOrderer().getName())
                .phoneNumber(this.getOrderer().getPhoneNumber())
                .build();
        o.getItems().addAll(this.convert2OrderItems(o));
        if (Order.verifyHaveAtLeastOneItem(o.getItems())) throw new Exception("Noting Items");
        o.calculateTotalAmount();
        return o;
    }


}