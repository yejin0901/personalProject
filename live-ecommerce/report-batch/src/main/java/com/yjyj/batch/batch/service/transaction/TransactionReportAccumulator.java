package com.yjyj.batch.batch.service.transaction;


import com.yjyj.batch.batch.domain.transaction.report.TransactionReport;
import com.yjyj.batch.batch.domain.transaction.report.TransactionReportMapRepository;
import com.yjyj.batch.batch.dto.transaction.log.TransactionLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionReportAccumulator {

  private final TransactionReportMapRepository repository;

  public void accumulate(TransactionLog transactionLog) {
    if (!"SUCCESS".equalsIgnoreCase(transactionLog.getTransactionStatus())) {
      return;
    }
    repository.put(TransactionReport.from(transactionLog));
  }

}