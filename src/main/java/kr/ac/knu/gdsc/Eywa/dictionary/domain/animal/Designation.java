package kr.ac.knu.gdsc.Eywa.dictionary.domain.animal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Designation {
    @Column(name = "designation_domestic")
    private String domestic;

    @Column(name = "designation_oversea")
    private String oversea;

    @Column(name = "designation_organization")
    private String organization;
}
