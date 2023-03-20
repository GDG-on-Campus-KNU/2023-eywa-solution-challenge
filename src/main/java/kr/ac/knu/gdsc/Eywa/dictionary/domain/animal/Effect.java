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
public class Effect {
    @Column(name = "effect_ecosystem",
            columnDefinition = "text")
    private String ecosystem;

    @Column(name = "effect_entity",
            columnDefinition = "text")
    private String entity;
}
