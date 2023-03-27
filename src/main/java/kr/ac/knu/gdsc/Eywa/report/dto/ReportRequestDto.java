package kr.ac.knu.gdsc.Eywa.report.dto;

import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReportRequestDto {
    @DecimalMin("0.0000001")
    @Digits(integer = 10, fraction = 6)
    private BigDecimal longitude;
    @DecimalMin("0.0000001")
    @Digits(integer = 10, fraction = 6)
    private BigDecimal latitude;
    private Long dictionaryId;
}
