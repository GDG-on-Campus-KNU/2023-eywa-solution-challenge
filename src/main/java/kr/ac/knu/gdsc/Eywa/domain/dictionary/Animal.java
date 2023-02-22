package kr.ac.knu.gdsc.Eywa.domain.dictionary;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@DiscriminatorValue("Animal")
public class Animal extends dictionary{
    private String shape;

    @Column(name = "ecological_habitat")
    private String habitat;

    @Column(name = "ecological_food_chain")
    private String foodChain;

    @Column(name = "ecological_lifespan")
    private String lifeSpan;

    @Column(name = "ecological_etc")
    private String etc;

    @Column(name = "introduction_origin")
    private String origin;

    @Column(name = "introduction_period")
    private String period;

    @Column(name = "introduction_purpose")
    private String purpose;

    private String distribution;

    @Column(name = "effect_ecosystem")
    private String effect;

    @Column(name = "effect_entity")
    private String entity;

    @Column(name = "regulate_past")
    private String  regulatePast;

    @Column(name = "regulate_reason")
    private String regulateReason;

    @Column(name = "regulate_method")
    private String regulateMethod;

    @Column(name = "designation_domestic")
    private String domestic;

    @Column(name = "designation_oversea")
    private String oversea;

    @Column(name = "designation_organization")
    private String organization;






}
