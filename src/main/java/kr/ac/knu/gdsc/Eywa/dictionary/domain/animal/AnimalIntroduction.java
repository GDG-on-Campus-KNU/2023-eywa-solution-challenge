package kr.ac.knu.gdsc.Eywa.dictionary.domain.animal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AnimalIntroduction {
    @Column(name = "introduction_origin",
            columnDefinition = "text")
    private String origin;

    @Column(name = "introduction_period",
            columnDefinition = "text")
    private String period;

    @Column(name = "introduction_purpose",
            columnDefinition = "text")
    private String purpose;
}
