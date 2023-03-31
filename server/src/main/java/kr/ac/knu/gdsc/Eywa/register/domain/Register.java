package kr.ac.knu.gdsc.Eywa.register.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import kr.ac.knu.gdsc.Eywa.common.domain.BaseTimeEntity;
import kr.ac.knu.gdsc.Eywa.dictionary.domain.Dictionary;
import kr.ac.knu.gdsc.Eywa.member.domain.Member;
import kr.ac.knu.gdsc.Eywa.register.dto.RegisterResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Register extends BaseTimeEntity {
  @Id
  @GeneratedValue
  @Column(name = "register_id")
  private Long id;

  @DecimalMin("0.0000001")
  @Digits(integer = 10, fraction = 6)
  private BigDecimal latitude;

  @DecimalMin("0.0000001")
  @Digits(integer = 10, fraction = 6)
  private BigDecimal longitude;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  @JsonIgnore
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dictionary_id")
  private Dictionary dictionary;

  @Builder
  public Register(BigDecimal latitude, BigDecimal longitude, Member member, Dictionary dictionary) {
    this.latitude = latitude;
    this.longitude = longitude;
    this.member = member;
    this.dictionary = dictionary;
  }

  // convert to dto
  public RegisterResponseDto toDto() {
    return new RegisterResponseDto(this.latitude, this.longitude, this.getCreatedAt());
  }
}
