package kr.ac.knu.gdsc.Eywa.domain.dictionary.plant;

import kr.ac.knu.gdsc.Eywa.domain.dictionary.dictionary;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@DiscriminatorValue("Plant")
public class Plant extends dictionary {

    @Embedded
    private Shape shape;

    @Embedded
    private PlantEcological ecological;

    @Embedded
    private PlantIntroduction introduction;
}
