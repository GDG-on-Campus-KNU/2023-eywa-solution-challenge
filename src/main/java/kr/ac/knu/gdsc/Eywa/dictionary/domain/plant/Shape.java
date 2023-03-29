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
public class Shape {
  @Column(name = "shape_size", columnDefinition = "text")
  private String size;

  @Column(name = "shape_stem", columnDefinition = "text")
  private String stem;

  @Column(name = "shape_leaf", columnDefinition = "text")
  private String leaf;

  @Column(name = "shape_flower_description", columnDefinition = "text")
  private String flowerDescription;

  @Column(name = "shape_flower_color", columnDefinition = "text")
  private String flowerColor;

  @Column(name = "shape_fruit", columnDefinition = "text")
  private String fruit;
}
