package kr.ac.knu.gdsc.Eywa.report.controller;

import kr.ac.knu.gdsc.Eywa.dictionary.domain.Dictionary;
import kr.ac.knu.gdsc.Eywa.dictionary.service.DictionaryService;
import kr.ac.knu.gdsc.Eywa.report.domain.Report;
import kr.ac.knu.gdsc.Eywa.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value="/reports")
public class ReportController {
    private final ReportService reportService;
    private final DictionaryService dictionaryService;

    @Autowired
    public ReportController(ReportService reportService, DictionaryService dictionaryService) {
        this.reportService = reportService;
        this.dictionaryService = dictionaryService;
    }

    @RequestMapping(value="/", method= RequestMethod.GET)
    public List<Report> getReportList() {
        return this.reportService.getReportList();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Report getReport(@PathVariable Long id) {
        Optional<Report> report = this.reportService.getReport(id);
        return report.orElse(null);
    }

    @RequestMapping(value="/", method= RequestMethod.POST)
    public void saveReport(@RequestBody HashMap<String, Objects> reportMap) {
        BigDecimal longitude = new BigDecimal(reportMap.get("longitude").toString());
        BigDecimal latitude = new BigDecimal(reportMap.get("latitude").toString());
        Long dictionaryId = Long.parseLong(reportMap.get("dictionary_id").toString());
        Report report = new Report().builder()
                .longitude(longitude)
                .latitude(latitude)
                .dictionary(this.dictionaryService.getDictionary(dictionaryId).orElse(null))
                .build();
        this.reportService.saveReport(report);
    }
}
