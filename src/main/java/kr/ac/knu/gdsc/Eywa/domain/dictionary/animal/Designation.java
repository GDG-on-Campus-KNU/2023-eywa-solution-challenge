package kr.ac.knu.gdsc.Eywa.domain.dictionary.animal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Designation {
    @Column(name = "designation_domestic")
    private String domestic;

    @Column(name = "designation_oversea")
    private String oversea;

    @Column(name = "designation_organization")
    private String organization;
}
