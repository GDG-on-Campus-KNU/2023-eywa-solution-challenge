package kr.ac.knu.gdsc.Eywa.domain.dictionary.plant;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Shape {
    @Column(name = "shape_size")
    private String size;

    @Column(name = "shape_stem")
    private String stem;

    @Column(name = "shape_leaf")
    private String leaf;

    @Column(name = "shape_flower_description")
    private String flowerDescription;

    @Column(name = "shape_flower_color")
    private String flowerColor;

    @Column(name = "shape_fruit")
    private String fruit;
}
