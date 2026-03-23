package com.yjyj.ecommerce.report.representation.product.report;


import com.yjyj.ecommerce.report.application.service.product.report.ProductStatusReportResult;
import com.yjyj.ecommerce.report.domain.product.ProductStatus;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductStatusReportResponse {

  private LocalDate statDate;
  private ProductStatus productStatus;
  private Long productCount;
  private Double avgStockQuantity;

  public static ProductStatusReportResponse from(ProductStatusReportResult result) {
    return new ProductStatusReportResponse(
        result.getStatDate(),
        result.getProductStatus(),
        result.getProductCount(),
        result.getAvgStockQuantity()
    );
  }
}
