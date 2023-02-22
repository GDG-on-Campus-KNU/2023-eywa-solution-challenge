package kr.ac.knu.gdsc.Eywa.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
public class Level {
    @Id @GeneratedValue
    @Column(name = "level_id")
    private Long id;

    private int level;

    @Column(name="max_exp")
    private int maxExp;

    @Column(name="min_exp")
    private int minExp;


}
