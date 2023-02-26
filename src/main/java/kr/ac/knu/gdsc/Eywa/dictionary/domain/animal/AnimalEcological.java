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
