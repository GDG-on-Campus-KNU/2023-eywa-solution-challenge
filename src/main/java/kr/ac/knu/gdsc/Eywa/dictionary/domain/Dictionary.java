package kr.ac.knu.gdsc.Eywa.dictionary.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import kr.ac.knu.gdsc.Eywa.dictionary.dto.DictionaryDto;
import kr.ac.knu.gdsc.Eywa.register.domain.Register;
import kr.ac.knu.gdsc.Eywa.register.dto.RegisterResponseDto;
import kr.ac.knu.gdsc.Eywa.report.domain.Report;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn // 하위 테이블의 구분 칼럼 생성 default = DTYPE
@SequenceGenerator(
    name = "dictionary_seq_generator",
    sequenceName = "dictionary_seq",
    initialValue = 1,
    allocationSize = 1)
public abstract class Dictionary {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dictionary_seq_generator")
  @Column(name = "dictionary_id")
  private Long id;

  private String koreanName;
  private String englishName;

  @Column(columnDefinition = "text")
  private String summary;

  private String kind;

  private String image;

  @OneToMany(mappedBy = "dictionary")
  @JsonIgnore
  private List<Report> reports = new ArrayList<>();

  @OneToMany(mappedBy = "dictionary")
  @JsonIgnore
  private List<Register> registers = new ArrayList<>();

  public Dictionary(
      String koreanName, String englishName, String summary, String kind, String image) {
    this.koreanName = koreanName;
    this.englishName = englishName;
    this.summary = summary;
    this.kind = kind;
    this.image = image;
  }

  // convert to dto
  public DictionaryDto toDto(Long memberId) {
    RegisterResponseDto registerResponseDto = null;
    for (Register register : this.registers) {
      if (register.getMember().getId().equals(memberId)) {
        registerResponseDto = register.toDto();
      }
    }
    return new DictionaryDto(this, registerResponseDto);
  }

  public DictionaryDto toDto() {
    return new DictionaryDto(this, null);
  }
}
