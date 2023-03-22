package kr.ac.knu.gdsc.Eywa.register.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class RegisterRequestDto {
    // 위치
    private BigDecimal latitude;
    private BigDecimal longitude;

    // 사전 저장 key
    private Long dictionaryId;
}
