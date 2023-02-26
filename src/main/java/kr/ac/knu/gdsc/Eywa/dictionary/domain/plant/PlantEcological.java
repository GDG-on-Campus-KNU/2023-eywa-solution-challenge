package kr.ac.knu.gdsc.Eywa.dictionary.domain.plant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PlantEcological {
    @Column(name = "ecological_growth_period")
    private String growthPeriod;

    @Column(name = "ecological_bloom_period")
    private String bloomPeriod;

    @Column(name = "ecological_habitat_domestic")
    private String habitatDomestic;

    @Column(name = "ecological_habitat_overseas")
    private String habitatOverseas;
}
