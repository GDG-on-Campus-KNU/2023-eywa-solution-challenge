package kr.ac.knu.gdsc.Eywa.register.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.ac.knu.gdsc.Eywa.common.domain.BaseTimeEntity;
import kr.ac.knu.gdsc.Eywa.dictionary.domain.Dictionary;
import kr.ac.knu.gdsc.Eywa.members.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Entity
public class Register extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "register_id")
    private Long id;

    private BigDecimal latitude;

    private BigDecimal longitude;

    @ManyToOne

    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    @ManyToOne
    @JoinColumn(name = "dictionary_id")
    private Dictionary dictionary;

    @Builder
    public Register(BigDecimal latitude, BigDecimal longitude, Member member, Dictionary dictionary) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.member = member;
        this.dictionary = dictionary;
    }
}
