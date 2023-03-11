package kr.ac.knu.gdsc.Eywa.register.dto;

import kr.ac.knu.gdsc.Eywa.dictionary.domain.Dictionary;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class RegisterSaveResponseDto {
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Dictionary dictionary;
}
