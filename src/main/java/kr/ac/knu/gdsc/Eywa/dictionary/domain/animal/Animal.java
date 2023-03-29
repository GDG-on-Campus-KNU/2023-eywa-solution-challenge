package kr.ac.knu.gdsc.Eywa.dictionary.domain.animal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import kr.ac.knu.gdsc.Eywa.dictionary.domain.Dictionary;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@DiscriminatorValue("Animal")
public class Animal extends Dictionary {
  @Column(columnDefinition = "text")
  private String shape;

  @Embedded private AnimalEcological ecological;

  @Embedded private AnimalIntroduction introduction;

  @Column(columnDefinition = "text")
  private String distribution;

  @Embedded private Effect effect;

  @Embedded private Regulate regulate;

  @Embedded private Designation designation;

  @Builder
  public Animal(
      String korName,
      String engName,
      String summary,
      String kind,
      String image,
      String shape,
      AnimalEcological ecological,
      AnimalIntroduction introduction,
      String distribution,
      Effect effect,
      Regulate regulate,
      Designation designation) {
    super(korName, engName, summary, kind, image);
    this.shape = shape;
    this.ecological = ecological;
    this.introduction = introduction;
    this.distribution = distribution;
    this.effect = effect;
    this.regulate = regulate;
    this.designation = designation;
  }
}
