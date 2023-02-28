package kr.ac.knu.gdsc.Eywa.dictionary.domain.animal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Regulate {
    @Column(name = "regulate_past",
            columnDefinition = "text")
    private String past;

    @Column(name = "regulate_reason",
            columnDefinition = "text")
    private String reason;

    @Column(name = "regulate_method",
            columnDefinition = "text")
    private String method;
}
