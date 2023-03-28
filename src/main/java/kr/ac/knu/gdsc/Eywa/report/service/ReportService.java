package kr.ac.knu.gdsc.Eywa.report.service;

import kr.ac.knu.gdsc.Eywa.report.domain.Report;
import kr.ac.knu.gdsc.Eywa.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    public void saveReport(Report report) {
        reportRepository.save(report);
    }

    public Optional<Report> getReport(Long reportId) {
        return reportRepository.findById(reportId);
    }

    public List<Report> getReportList() {
        return reportRepository.findAll();
    }

    public List<Report> getReportListOfMember(Long memberId) {
        return reportRepository.findAllByMemberId(memberId);
    }
}
