package kr.ac.knu.gdsc.Eywa.domain.dictionary;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn //하위 테이블의 구분 칼럼 생성 default = DTYPE
public abstract class dictionary {
    @Id @GeneratedValue
    @Column(name = "dictionary_id")
    private Long id;

    @Column(name = "korean_name")
    private String korName;

    @Column(name = "english_name")
    private String EngName;

    private String summary;

    private String kind;

    private String image;
}
