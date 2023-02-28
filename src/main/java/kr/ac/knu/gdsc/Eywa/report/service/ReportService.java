package kr.ac.knu.gdsc.Eywa.report.service;

import kr.ac.knu.gdsc.Eywa.report.domain.Report;
import kr.ac.knu.gdsc.Eywa.report.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public void saveReport(Report report) {
        reportRepository.save(report);
    }

    public Optional<Report> getReport(Long id) {
        return reportRepository.findById(id);
    }

    public List<Report> getReportList() {
        return reportRepository.findAll();
    }
}
