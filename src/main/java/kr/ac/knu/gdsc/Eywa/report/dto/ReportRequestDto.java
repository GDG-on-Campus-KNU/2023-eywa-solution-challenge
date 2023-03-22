package kr.ac.knu.gdsc.Eywa.report.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportRequestDto {
    private BigDecimal longitude;
    private BigDecimal latitude;
    private Long dictionaryId;
}
