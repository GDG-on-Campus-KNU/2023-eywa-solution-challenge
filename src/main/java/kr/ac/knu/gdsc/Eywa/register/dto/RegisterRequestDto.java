package kr.ac.knu.gdsc.Eywa.register.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class RegisterRequestDto {
    // 위치
    @DecimalMin("0.0000001")
    @Digits(integer = 10, fraction = 6)
    private BigDecimal latitude;
    @DecimalMin("0.0000001")
    @Digits(integer = 10, fraction = 6)
    private BigDecimal longitude;

    // 사전 저장 key
    private Long dictionaryId;
}
