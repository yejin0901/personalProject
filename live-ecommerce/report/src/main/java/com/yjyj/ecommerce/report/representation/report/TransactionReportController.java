package com.yjyj.ecommerce.report.representation.report;

import com.yjyj.ecommerce.report.application.service.transaction.report.TransactionReportService;
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

@RequestMapping("/v1/transactions/reports")
@RestController
@RequiredArgsConstructor
@Tag(name = "거래 보고서 API")
public class TransactionReportController {

  private final TransactionReportService transactionReportService;

  @GetMapping("")
  @Operation(summary = "거래 보고서 조회")
  public TransactionReportResponses getTransactionReports(
      @RequestParam("dt") @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
    return TransactionReportResponses.from(transactionReportService.findByDate(date));
  }

}
