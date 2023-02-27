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
public class Shape {
    @Column(name = "shape_size",
            columnDefinition = "clob")
    private String size;

    @Column(name = "shape_stem",
            columnDefinition = "clob")
    private String stem;

    @Column(name = "shape_leaf",
            columnDefinition = "clob")
    private String leaf;

    @Column(name = "shape_flower_description",
            columnDefinition = "clob")
    private String flowerDescription;

    @Column(name = "shape_flower_color",
            columnDefinition = "clob")
    private String flowerColor;

    @Column(name = "shape_fruit",
            columnDefinition = "clob")
    private String fruit;
}
