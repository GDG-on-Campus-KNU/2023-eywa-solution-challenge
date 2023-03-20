package kr.ac.knu.gdsc.Eywa.dictionary.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.ac.knu.gdsc.Eywa.register.domain.Register;
import kr.ac.knu.gdsc.Eywa.report.domain.Report;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn //하위 테이블의 구분 칼럼 생성 default = DTYPE
public abstract class Dictionary {
    @Id @GeneratedValue
    @Column(name = "dictionary_id")
    private Long id;

    private String koreanName;
    private String englishName;

    @Column(columnDefinition = "text")
    private String summary;

    private String kind;

    private String image;

    @OneToMany(mappedBy = "dictionary")
    @JsonIgnore
    private List<Report> reports = new ArrayList<>();

    @OneToMany(mappedBy = "dictionary")
    @JsonIgnore
    private List<Register> registers = new ArrayList<>();

    public Dictionary(String koreanName, String englishName, String summary, String kind, String image) {
        this.koreanName = koreanName;
        this.englishName = englishName;
        this.summary = summary;
        this.kind = kind;
        this.image = image;
    }

    /**
     * 연관관계 편의 메서드
     */


    /**
     * 비즈니스 로직
     */
}
