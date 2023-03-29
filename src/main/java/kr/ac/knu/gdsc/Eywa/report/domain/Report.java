package kr.ac.knu.gdsc.Eywa.report.domain;

import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import kr.ac.knu.gdsc.Eywa.common.domain.BaseTimeEntity;
import kr.ac.knu.gdsc.Eywa.dictionary.domain.Dictionary;
import kr.ac.knu.gdsc.Eywa.member.domain.Member;
import kr.ac.knu.gdsc.Eywa.report.dto.ReportResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@SequenceGenerator(
    name = "report_seq_generator",
    sequenceName = "report_seq",
    initialValue = 1,
    allocationSize = 1)
public class Report extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_seq")
  @Column(name = "report_id")
  private Long id;

  @DecimalMin("0.0000001")
  @Digits(integer = 10, fraction = 6)
  private BigDecimal latitude;

  @DecimalMin("0.0000001")
  @Digits(integer = 10, fraction = 6)
  private BigDecimal longitude;

  private String picture;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dictionary_id")
  private Dictionary dictionary;

  @Builder
  public Report(
      BigDecimal latitude,
      BigDecimal longitude,
      String picture,
      Member member,
      Dictionary dictionary) {
    this.latitude = latitude;
    this.longitude = longitude;
    this.picture = picture;
    this.member = member;
    this.dictionary = dictionary;
  }

  public ReportResponseDto toDto() {
    return ReportResponseDto.builder()
        .id(id)
        .latitude(latitude)
        .longitude(longitude)
        .picture(picture)
        .dictionary(dictionary.toDto())
        .member(member.toDto())
        .createdAt(getCreatedAt())
        .build();
  }
}
