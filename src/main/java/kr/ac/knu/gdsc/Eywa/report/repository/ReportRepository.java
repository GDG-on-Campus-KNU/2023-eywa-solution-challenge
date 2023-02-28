package kr.ac.knu.gdsc.Eywa.report.repository;

import kr.ac.knu.gdsc.Eywa.report.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findById(Long id);
    List<Report> findAll();
}
