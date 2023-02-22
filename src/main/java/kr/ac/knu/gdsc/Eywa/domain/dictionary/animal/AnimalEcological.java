package kr.ac.knu.gdsc.Eywa.domain.dictionary.animal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AnimalEcological {
    @Column(name = "ecological_habitat")
    private String habitat;

    @Column(name = "ecological_food_chain")
    private String foodChain;

    @Column(name = "ecological_lifespan")
    private String lifeSpan;

    @Column(name = "ecological_etc")
    private String etc;
}
