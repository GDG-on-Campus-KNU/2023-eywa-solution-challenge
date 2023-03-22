package kr.ac.knu.gdsc.Eywa.register.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterRequestDto {
    // 위치
    private BigDecimal latitude;
    private BigDecimal longitude;

    // 사전 저장 key
    private Long dictionaryId;
}
