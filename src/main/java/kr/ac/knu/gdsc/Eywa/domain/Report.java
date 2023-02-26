package kr.ac.knu.gdsc.Eywa.domain;

import kr.ac.knu.gdsc.Eywa.common.domain.BaseTimeEntity;
import kr.ac.knu.gdsc.Eywa.domain.dictionary.Dictionary;
import kr.ac.knu.gdsc.Eywa.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Report extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "report_id")
    private Long id;
    private BigDecimal latitude;
    private BigDecimal longitude;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "dictionary_id")
    private Dictionary dictionary;

    @Builder
    public Report(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
