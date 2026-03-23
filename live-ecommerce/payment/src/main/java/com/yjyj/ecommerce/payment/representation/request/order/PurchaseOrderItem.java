package com.yjyj.ecommerce.payment.representation.request.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderItem {
    @Min(1)
    private int itemIdx;

    private UUID productId;

    @NotBlank
    private String productName;

    private int price;    // 가격

    @Min(1)
    private int quantity; // 수량

    private int amounts;  // price * quantity

}
