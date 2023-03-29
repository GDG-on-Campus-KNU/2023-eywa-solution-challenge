package kr.ac.knu.gdsc.Eywa.dictionary.domain.animal;

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
public class Designation {
  @Column(name = "designation_domestic", columnDefinition = "text")
  private String domestic;

  @Column(name = "designation_overseas", columnDefinition = "text")
  private String overseas;

  @Column(name = "designation_organization", columnDefinition = "text")
  private String organization;
}
