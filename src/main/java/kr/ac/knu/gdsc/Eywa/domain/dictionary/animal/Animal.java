package kr.ac.knu.gdsc.Eywa.domain.dictionary.animal;

import kr.ac.knu.gdsc.Eywa.domain.dictionary.Dictionary;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Getter
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
}
