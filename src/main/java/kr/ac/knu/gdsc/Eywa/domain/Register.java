package kr.ac.knu.gdsc.Eywa.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
public class Register {
    @Id @GeneratedValue
    @Column(name = "register_id")
    private Long id;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private LocalDateTime time;

}
