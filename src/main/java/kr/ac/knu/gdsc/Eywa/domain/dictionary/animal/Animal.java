package kr.ac.knu.gdsc.Eywa.domain.dictionary.animal;

import kr.ac.knu.gdsc.Eywa.domain.dictionary.Dictionary;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
@Getter
@NoArgsConstructor
@Entity
@DiscriminatorValue("Animal")
public class Animal extends Dictionary {
    private String shape;

    @Embedded
    private AnimalEcological ecological;

    @Embedded
    private AnimalIntroduction introduction;

    private String distribution;

    @Embedded
    private Effect effect;

    @Embedded
    private Regulate regulate;

    @Embedded
    private Designation designation;
    @Builder
    public Animal(String korName, String engName, String summary, String kind, String image, String shape, AnimalEcological ecological, AnimalIntroduction introduction, String distribution, Effect effect, Regulate regulate, Designation designation) {
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
