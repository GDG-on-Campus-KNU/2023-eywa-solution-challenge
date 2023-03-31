package kr.ac.knu.gdsc.Eywa.dictionary.domain.plant;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PlantIntroduction {
  @Column(name = "introduction_origin", columnDefinition = "text")
  private String origin;

  @Column(name = "introduction_period", columnDefinition = "text")
  private String period;
}
