package kr.ac.knu.gdsc.Eywa.report.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import kr.ac.knu.gdsc.Eywa.dictionary.dto.DictionaryDto;
import kr.ac.knu.gdsc.Eywa.member.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReportResponseDto {
  private Long id;

  @DecimalMin("0.0000001")
  @Digits(integer = 10, fraction = 6)
  private BigDecimal longitude;

  @DecimalMin("0.0000001")
  @Digits(integer = 10, fraction = 6)
  private BigDecimal latitude;

  private String picture;
  private DictionaryDto dictionary;
  private MemberDto member;
  private LocalDateTime createdAt;
}
