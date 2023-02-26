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
public class Effect {
    @Column(name = "effect_ecosystem")
    private String effect;

    @Column(name = "effect_entity")
    private String entity;
}
