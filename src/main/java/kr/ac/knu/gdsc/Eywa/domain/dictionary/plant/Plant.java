package kr.ac.knu.gdsc.Eywa.domain.dictionary.plant;

import kr.ac.knu.gdsc.Eywa.domain.dictionary.Dictionary;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@DiscriminatorValue("Plant")
public class Plant extends Dictionary {

    @Embedded
    private Shape shape;

    @Embedded
    private PlantEcological ecological;

    @Embedded
    private PlantIntroduction introduction;
}
