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
public class AnimalEcological {
  @Column(name = "ecological_habitat", columnDefinition = "text")
  private String habitat;

  @Column(name = "ecological_lifespan", columnDefinition = "text")
  private String lifespan;

  @Column(name = "ecological_etc", columnDefinition = "text")
  private String etc;
}
