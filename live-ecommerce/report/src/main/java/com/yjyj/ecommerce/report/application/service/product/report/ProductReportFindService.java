package com.yjyj.ecommerce.report.application.service.product.report;

import com.yjyj.ecommerce.report.domain.product.report.BrandReportRepository;
import com.yjyj.ecommerce.report.domain.product.report.CategoryReportRepository;
import com.yjyj.ecommerce.report.domain.product.report.ManufacturerReportRepository;
import com.yjyj.ecommerce.report.domain.product.report.ProductStatusReportRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductReportFindService {

  private final BrandReportRepository brandReportRepository;
  private final CategoryReportRepository categoryReportRepository;
  private final ManufacturerReportRepository manufacturerReportRepository;
  private final ProductStatusReportRepository productStatusReportRepository;

  public ProductReportResults findReports(LocalDate date) {
    return ProductReportResults.of(brandReportRepository.findAllByStatDate(date),
        categoryReportRepository.findAllByStatDate(date),
        manufacturerReportRepository.findAllByStatDate(date),
        productStatusReportRepository.findAllByStatDate(date)
    );
  }
}
