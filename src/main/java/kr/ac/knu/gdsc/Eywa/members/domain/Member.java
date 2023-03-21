package kr.ac.knu.gdsc.Eywa.members.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.ac.knu.gdsc.Eywa.common.domain.BaseTimeEntity;
import kr.ac.knu.gdsc.Eywa.register.domain.Register;
import kr.ac.knu.gdsc.Eywa.report.domain.Report;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {
    @JsonIgnore
    @Id @GeneratedValue()
    @Column(name = "member_id")
    private Long id;

    @NotNull
    private String sub;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private int exp;

    @NotNull
    private String picture;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    @OneToOne
    @JoinColumn(name = "level_id")
    private Level level;

    @OneToMany(mappedBy = "member")
    private List<Register> registers = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Report> reports = new ArrayList<>();

    @Builder
    public Member(String sub, String name, String picture, String email, String role) {
        this.sub = sub;
        this.name = name;
        this.picture = picture;
        this.email = email;
        this.role = Role.valueOf(role);
    }
}
