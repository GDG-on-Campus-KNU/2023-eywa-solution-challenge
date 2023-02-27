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
public class AnimalEcological {
    @Column(name = "ecological_habitat",
            columnDefinition = "clob")
    private String habitat;

    @Column(name = "ecological_lifespan",
            columnDefinition = "clob")
    private String lifeSpan;

    @Column(name = "ecological_etc",
            columnDefinition = "clob")
    private String etc;
}
