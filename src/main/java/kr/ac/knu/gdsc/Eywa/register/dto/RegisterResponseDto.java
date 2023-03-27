package kr.ac.knu.gdsc.Eywa.register.dto;

import kr.ac.knu.gdsc.Eywa.dictionary.domain.Dictionary;
import kr.ac.knu.gdsc.Eywa.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RegisterResponseDto {

    @DecimalMin("0.0000001")
    @Digits(integer = 10, fraction = 6)
    private BigDecimal latitude;

    @DecimalMin("0.0000001")
    @Digits(integer = 10, fraction = 6)
    private BigDecimal longitude;
    private LocalDateTime createdAt;
}
