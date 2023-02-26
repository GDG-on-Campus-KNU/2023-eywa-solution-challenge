package kr.ac.knu.gdsc.Eywa.domain.dictionary.animal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Regulate {
    @Column(name = "regulate_past")
    private String  past;

    @Column(name = "regulate_reason")
    private String reason;

    @Column(name = "regulate_method")
    private String method;
}
