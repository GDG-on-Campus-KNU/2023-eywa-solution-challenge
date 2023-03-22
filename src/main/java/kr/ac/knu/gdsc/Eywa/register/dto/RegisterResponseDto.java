package kr.ac.knu.gdsc.Eywa.register.dto;

import kr.ac.knu.gdsc.Eywa.dictionary.domain.Dictionary;
import kr.ac.knu.gdsc.Eywa.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RegisterResponseDto {
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalDateTime createdAt;
}
