package kr.ac.knu.gdsc.Eywa.domain.dictionary.plant;

import kr.ac.knu.gdsc.Eywa.domain.dictionary.Dictionary;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor
@Entity
@DiscriminatorValue("Plant")
public class Plant extends Dictionary {

    @Embedded
    private Shape shape;

    @Embedded
    private PlantEcological ecological;

    @Embedded
    private PlantIntroduction introduction;

    @Builder
    public Plant(String korName, String engName, String summary, String kind, String image, Shape shape, PlantEcological ecological, PlantIntroduction introduction) {
        super(korName, engName, summary, kind, image);
        this.shape = shape;
        this.ecological = ecological;
        this.introduction = introduction;
    }
}
