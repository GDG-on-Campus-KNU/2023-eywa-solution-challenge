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
public class Designation {
    @Column(name = "designation_domestic",
            columnDefinition = "text")
    private String domestic;

    @Column(name = "designation_oversea",
            columnDefinition = "text")
    private String oversea;

    @Column(name = "designation_organization",
            columnDefinition = "text")
    private String organization;
}
