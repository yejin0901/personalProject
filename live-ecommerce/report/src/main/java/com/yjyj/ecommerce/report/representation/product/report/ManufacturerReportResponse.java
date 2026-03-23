package com.yjyj.ecommerce.report.representation.product.report;

import com.yjyj.ecommerce.report.application.service.product.report.ManufacturerReportResult;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ManufacturerReportResponse {

  private LocalDate statDate;
  private String manufacturer;
  private Long productCount;
  private Double avgSalesPrice;
  private Long totalStockQuantity;

  public static ManufacturerReportResponse from(ManufacturerReportResult result) {
    return new ManufacturerReportResponse(
        result.getStatDate(),
        result.getManufacturer(),
        result.getProductCount(),
        result.getAvgSalesPrice(),
        result.getTotalStockQuantity()

    );
  }
}
