package kr.ac.knu.gdsc.Eywa.domain.dictionary;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@DiscriminatorValue("Plant")
public class Plant extends dictionary {

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

    @Column(name = "ecological_growth_period")
    private String growthPeriod;

    @Column(name = "ecological_bloom_period")
    private String bloomPeriod;

    @Column(name = "ecological_habitat_domestic")
    private String habitatDomestic;

    @Column(name = "ecological_habitat_overseas")
    private String habitatOverseas;

    @Column(name = "introduction_origin")
    private String introOrigin;

    @Column(name = "introduction_period")
    private String introPeriod;
}
