package com.yjyj.batch.batch.domain.product.report;

import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryReportBatchRepository extends JpaRepository<CategoryReport, CategoryReportId> {

  Long countByStatDate(LocalDate statDate);
}
