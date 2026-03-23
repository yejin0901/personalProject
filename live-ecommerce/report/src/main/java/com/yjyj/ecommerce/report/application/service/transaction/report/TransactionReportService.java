package com.yjyj.ecommerce.report.application.service.transaction.report;


import com.yjyj.ecommerce.report.domain.transaction.report.TransactionReportRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionReportService {

  private final TransactionReportRepository repository;

  public TransactionReportResults findByDate(LocalDate date) {
    return TransactionReportResults.from(repository.findAllByTransactionDate(date));
  }
}