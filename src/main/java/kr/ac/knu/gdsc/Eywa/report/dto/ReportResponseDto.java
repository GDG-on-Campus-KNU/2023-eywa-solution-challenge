package kr.ac.knu.gdsc.Eywa.report.dto;

import kr.ac.knu.gdsc.Eywa.dictionary.dto.DictionaryDto;
import kr.ac.knu.gdsc.Eywa.member.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class ReportResponseDto {
    private Long id;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String picture;
    private DictionaryDto dictionary;
    private MemberDto member;
    private LocalDateTime createdAt;
}
