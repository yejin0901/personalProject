package com.yjyj.batch.batch.service.product.report;

import com.yjyj.batch.batch.domain.product.report.BrandReportRepository;
import com.yjyj.batch.batch.domain.product.report.CategoryReportBatchRepository;
import com.yjyj.batch.batch.domain.product.report.ManufacturerReportRepository;
import com.yjyj.batch.batch.domain.product.report.ProductStatusReportRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductReportService {

  private final BrandReportRepository brandReportRepository;
  private final CategoryReportBatchRepository categoryReportBatchRepository;
  private final ManufacturerReportRepository manufacturerReportRepository;
  private final ProductStatusReportRepository productStatusReportRepository;

  public Long countCategoryReport(LocalDate statDate) {
    return categoryReportBatchRepository.countByStatDate(statDate);
  }

  public Long countBrandReport(LocalDate statDate) {
    return brandReportRepository.countByStatDate(statDate);
  }

  public Long countManufacturerReport(LocalDate statDate) {
    return manufacturerReportRepository.countByStatDate(statDate);
  }

  public Long countProductStatusReport(LocalDate statDate) {
    return productStatusReportRepository.countByStatDate(statDate);
  }
}
