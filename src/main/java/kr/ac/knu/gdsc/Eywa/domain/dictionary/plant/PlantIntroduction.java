package kr.ac.knu.gdsc.Eywa.domain.dictionary.plant;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PlantIntroduction {
    @Column(name = "introduction_origin")
    private String origin;

    @Column(name = "introduction_period")
    private String period;
}
