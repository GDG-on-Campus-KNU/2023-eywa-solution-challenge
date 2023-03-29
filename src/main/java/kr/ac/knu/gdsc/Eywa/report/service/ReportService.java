package kr.ac.knu.gdsc.Eywa.report.service;

import java.util.List;
import kr.ac.knu.gdsc.Eywa.common.ErrorCode;
import kr.ac.knu.gdsc.Eywa.report.domain.Report;
import kr.ac.knu.gdsc.Eywa.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {
  private final ReportRepository reportRepository;

  public void saveReport(Report report) {
    reportRepository.save(report);
  }

  public Report getReport(Long reportId) {
    return reportRepository
        .findById(reportId)
        .orElseThrow(() -> new IllegalArgumentException(ErrorCode.REPORT_NOT_FOUND.getMessage()));
  }

  public List<Report> getReportList() {
    return reportRepository.findAll();
  }

  public List<Report> getReportListOfMember(Long memberId) {
    return reportRepository.findAllByMemberId(memberId);
  }
}
