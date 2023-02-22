package kr.ac.knu.gdsc.Eywa.domain.dictionary.animal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Effect {
    @Column(name = "effect_ecosystem")
    private String effect;

    @Column(name = "effect_entity")
    private String entity;
}
