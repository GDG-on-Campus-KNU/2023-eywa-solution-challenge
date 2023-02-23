package kr.ac.knu.gdsc.Eywa.domain.member;

import kr.ac.knu.gdsc.Eywa.domain.Level;
import kr.ac.knu.gdsc.Eywa.domain.Register;
import kr.ac.knu.gdsc.Eywa.domain.Report;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long Id;

    private String name;

    private int exp;

    private String image;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    @JoinColumn(name = "level_id")
    private Level level;

    @OneToMany(mappedBy = "member")
    private List<Register> registers = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Report> reports = new ArrayList<>();
}
