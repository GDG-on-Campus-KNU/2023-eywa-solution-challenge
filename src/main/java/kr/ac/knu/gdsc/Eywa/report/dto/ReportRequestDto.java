package kr.ac.knu.gdsc.Eywa.report.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReportRequestDto {
    private BigDecimal longitude;
    private BigDecimal latitude;
    private Long dictionaryId;
}
