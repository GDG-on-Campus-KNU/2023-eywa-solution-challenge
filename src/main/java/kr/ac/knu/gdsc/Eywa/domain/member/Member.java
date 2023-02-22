package kr.ac.knu.gdsc.Eywa.domain.member;

import kr.ac.knu.gdsc.Eywa.domain.member.Role;
import lombok.Getter;

import javax.persistence.*;

@Getter
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
}
