package kr.ac.knu.gdsc.Eywa.member.domain;

import kr.ac.knu.gdsc.Eywa.common.domain.BaseTimeEntity;
import kr.ac.knu.gdsc.Eywa.register.domain.Register;
import kr.ac.knu.gdsc.Eywa.report.domain.Report;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long Id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private int exp;

    private String image;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToOne
    @JoinColumn(name = "level_id")
    private Level level;

    @OneToMany(mappedBy = "member")
    private List<Register> registers = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Report> reports = new ArrayList<>();

    @Builder
    public Member(String name, String email, int exp, String image, Role role, Level level) {
        this.name = name;
        this.email = email;
        this.exp = exp;
        this.image = image;
        this.role = role;
        this.level = level;
    }
}
