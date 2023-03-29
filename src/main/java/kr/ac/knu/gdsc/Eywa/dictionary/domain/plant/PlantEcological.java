package kr.ac.knu.gdsc.Eywa.dictionary.domain.plant;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PlantEcological {
  @Column(name = "ecological_growth_period", columnDefinition = "text")
  private String growthPeriod;

  @Column(name = "ecological_bloom_period", columnDefinition = "text")
  private String bloomPeriod;

  @Column(name = "ecological_habitat_domestic", columnDefinition = "text")
  private String habitatDomestic;

  @Column(name = "econological_habitat_overseas", columnDefinition = "text")
  private String habitatOverseas;
}
