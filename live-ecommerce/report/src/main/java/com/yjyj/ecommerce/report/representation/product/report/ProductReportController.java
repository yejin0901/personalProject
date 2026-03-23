package com.yjyj.ecommerce.report.representation.product.report;

import com.yjyj.ecommerce.report.application.service.product.report.ProductReportFindService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/products/reports")
@RestController
@RequiredArgsConstructor
@Tag(name = "제품 보고서 API")
public class ProductReportController {

  private final ProductReportFindService productReportFindService;

  @GetMapping("")
  @Operation(summary = "제품 보고서 조회")
  public ProductReportResponse getProductReports(
      @RequestParam("dt") @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
    return ProductReportResponse.from(productReportFindService.findReports(date));
  }

}
