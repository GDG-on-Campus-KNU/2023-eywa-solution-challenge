package kr.ac.knu.gdsc.Eywa.domain;

import kr.ac.knu.gdsc.Eywa.domain.dictionary.Dictionary;
import kr.ac.knu.gdsc.Eywa.domain.member.Member;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
public class Report {
    @Id @GeneratedValue
    @Column(name = "report_id")
    private Long id;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "dictionary_id")
    private Dictionary dictionary;

}
