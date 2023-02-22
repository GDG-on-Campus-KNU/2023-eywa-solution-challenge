package kr.ac.knu.gdsc.Eywa.domain.dictionary.animal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AnimalIntroduction {
    @Column(name = "introduction_origin")
    private String origin;

    @Column(name = "introduction_period")
    private String period;

    @Column(name = "introduction_purpose")
    private String purpose;
}
