package kr.ac.knu.gdsc.Eywa.dictionary.domain.plant;

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
public class PlantEcological {
    @Column(name = "ecological_growth_period",
            columnDefinition = "clob")
    private String growthPeriod;

    @Column(name = "ecological_bloom_period",
            columnDefinition = "clob")
    private String bloomPeriod;

    @Column(name = "ecological_habitat_domestic",
            columnDefinition = "clob")
    private String habitatDomestic;

    @Column(name = "ecological_habitat_overseas",
            columnDefinition = "clob")
    private String habitatOverseas;
}
