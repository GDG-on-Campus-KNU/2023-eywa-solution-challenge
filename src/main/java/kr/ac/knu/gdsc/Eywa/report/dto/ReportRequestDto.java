package kr.ac.knu.gdsc.Eywa.report.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ReportRequestDto {
    private BigDecimal longitude;
    private BigDecimal latitude;
    private Long dictionaryId;
}
