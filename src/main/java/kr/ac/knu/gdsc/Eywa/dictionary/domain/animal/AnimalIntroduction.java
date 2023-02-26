package kr.ac.knu.gdsc.Eywa.dictionary.domain.animal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AnimalIntroduction {
    @Column(name = "introduction_origin")
    private String origin;

    @Column(name = "introduction_period")
    private String period;

    @Column(name = "introduction_purpose")
    private String purpose;
}
