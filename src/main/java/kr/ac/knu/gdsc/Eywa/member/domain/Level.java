package kr.ac.knu.gdsc.Eywa.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Level {
    @Id @GeneratedValue
    @Column(name = "level_id")
    private Long id;

    @Column(nullable = false)
    private int level;

    @Column(name="max_exp", nullable = false)
    private int maxExp;

    @Column(name="min_exp", nullable = false)
    private int minExp;

    @OneToOne(mappedBy = "level")
    private Member member;

    @Builder
    public Level(int level, int maxExp, int minExp) {
        this.level = level;
        this.maxExp = maxExp;
        this.minExp = minExp;
    }
}
