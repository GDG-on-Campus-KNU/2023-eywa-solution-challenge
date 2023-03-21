package kr.ac.knu.gdsc.Eywa.report.domain;

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
public class Report extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "report_id")
    private Long id;

    private BigDecimal latitude;
    private BigDecimal longitude;
    private String picture;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "dictionary_id")
    private Dictionary dictionary;

    @Builder
    public Report(BigDecimal latitude, BigDecimal longitude, String picture, Member member, Dictionary dictionary) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.picture = picture;
        this.member = member;
        this.dictionary = dictionary;
    }
}
