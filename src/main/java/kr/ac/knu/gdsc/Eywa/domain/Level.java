package kr.ac.knu.gdsc.Eywa.domain;

import kr.ac.knu.gdsc.Eywa.domain.member.Member;
import lombok.Getter;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "level")
    private Member member;
}
