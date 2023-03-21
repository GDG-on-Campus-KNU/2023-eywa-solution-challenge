package kr.ac.knu.gdsc.Eywa.report.controller;

import kr.ac.knu.gdsc.Eywa.dictionary.service.DictionaryService;
import kr.ac.knu.gdsc.Eywa.report.domain.Report;
import kr.ac.knu.gdsc.Eywa.report.dto.ReportRequestDto;
import kr.ac.knu.gdsc.Eywa.report.service.ReportService;
import kr.ac.knu.gdsc.Eywa.utils.CloudStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
    private final DictionaryService dictionaryService;
    private final CloudStorageService cloudStorageService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Report> getReportList() {
        return this.reportService.getReportList();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Report getReport(@PathVariable Long id) {
        Optional<Report> report = this.reportService.getReport(id);
        return report.orElse(null);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE
            }
    )
    public void saveReport(@RequestPart ReportRequestDto reportRequestDto,
                           @RequestPart MultipartFile image) {
        BigDecimal longitude = reportRequestDto.getLongitude();
        BigDecimal latitude = reportRequestDto.getLatitude();
        Long dictionaryId = reportRequestDto.getDictionaryId();

        Report report = new Report().builder()
                .longitude(longitude)
                .latitude(latitude)
                .dictionary(this.dictionaryService.getDictionary(dictionaryId).orElse(null))
                .build();

        this.reportService.saveReport(report);
        this.cloudStorageService.saveImage("registers", image);
    }
}
