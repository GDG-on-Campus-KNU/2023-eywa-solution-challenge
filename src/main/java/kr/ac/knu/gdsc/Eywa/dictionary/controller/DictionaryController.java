package kr.ac.knu.gdsc.Eywa.dictionary.controller;

import kr.ac.knu.gdsc.Eywa.dictionary.domain.Dictionary;
import kr.ac.knu.gdsc.Eywa.dictionary.domain.animal.*;
import kr.ac.knu.gdsc.Eywa.dictionary.domain.plant.Plant;
import kr.ac.knu.gdsc.Eywa.dictionary.domain.plant.PlantEcological;
import kr.ac.knu.gdsc.Eywa.dictionary.domain.plant.PlantIntroduction;
import kr.ac.knu.gdsc.Eywa.dictionary.domain.plant.Shape;
import kr.ac.knu.gdsc.Eywa.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/dictionary")
public class DictionaryController {
    private final DictionaryService dictionaryService;

    @Autowired
    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @RequestMapping(method=RequestMethod.POST)
    public void addDictionary(@RequestBody HashMap<String, Object> reqBody) {
        String korName = (String) reqBody.get("korean_name");
        String engName = (String) reqBody.get("english_name");
        String summary = (String) reqBody.get("summary");
        String kind = (String) reqBody.get("kind");
        String image = (String) reqBody.get("image");

        if (kind.equals("plant")) {
            HashMap<String, Object> shapeMap = (HashMap<String, Object>) reqBody.get("shape");
            HashMap<String, Object> flowerMap = (HashMap<String, Object>) shapeMap.get("flower");
            String flowerDescription = (String) flowerMap.get("description");
            String flowerColor = (String) flowerMap.get("color");
            String size = (String) shapeMap.get("size");
            String stem = (String) shapeMap.get("stem");
            String leaf = (String) shapeMap.get("leaf");
            String fruit = (String) shapeMap.get("fruit");
            Shape shape = new Shape(size, stem, leaf, flowerDescription, flowerColor, fruit);

            HashMap<String, Object> ecologicalMap = (HashMap<String, Object>) reqBody.get("ecological");
            HashMap<String, Object> habitat = (HashMap<String, Object>) ecologicalMap.get("habitat");
            String habitatDomestic = (String) habitat.get("domestic");
            String habitatOverseas = (String) habitat.get("overseas");
            String growthPeriod = (String) ecologicalMap.get("growth_period");
            String bloomPeriod = (String) ecologicalMap.get("bloom_period");
            PlantEcological ecological = new PlantEcological(growthPeriod, bloomPeriod, habitatDomestic, habitatOverseas);

            HashMap<String, Object> introductionMap = (HashMap<String, Object>) reqBody.get("introduction");
            String origin = (String) introductionMap.get("origin");
            String period = (String) introductionMap.get("period");
            PlantIntroduction introduction = new PlantIntroduction(origin, period);

            Plant plant = new Plant().builder()
                    .korName(korName)
                    .engName(engName)
                    .summary(summary)
                    .kind(kind)
                    .image(image)
                    .shape(shape)
                    .ecological(ecological)
                    .introduction(introduction)
                    .build();

            dictionaryService.saveDictionary(plant);
        } else {
            String shape = (String) reqBody.get("shape");

            HashMap<String, Object> ecologicalMap = (HashMap<String, Object>) reqBody.get("ecological");
            String habitat = (String) ecologicalMap.get("habitat");
            String lifespan = (String) ecologicalMap.get("lifespan");
            String etc = (String) ecologicalMap.get("etc");
            AnimalEcological ecological = new AnimalEcological(habitat, lifespan, etc);

            HashMap<String, Object> introductionMap = (HashMap<String, Object>) reqBody.get("introduction");
            String origin = (String) introductionMap.get("origin");
            String period = (String) introductionMap.get("period");
            String purpose = (String) introductionMap.get("purpose");
            AnimalIntroduction introduction = new AnimalIntroduction(origin, period, purpose);

            String distribution = (String) reqBody.get("distribution");

            HashMap<String, Object> effectMap = (HashMap<String, Object>) reqBody.get("effect");
            String ecosystem = (String) effectMap.get("ecosystem");
            String entity = (String) effectMap.get("entity");
            Effect effect = new Effect(ecosystem, entity);

            HashMap<String, Object> regulateMap = (HashMap<String, Object>) reqBody.get("regulate");
            String past = (String) regulateMap.get("past");
            String reason = (String) regulateMap.get("reason");
            String method = (String) regulateMap.get("method");
            Regulate regulate = new Regulate(past, reason, method);

            HashMap<String, Object> designationMap = (HashMap<String, Object>) reqBody.get("designation");
            String domestic = (String) designationMap.get("domestic");
            String overseas = (String) designationMap.get("overseas");
            String organization = (String) designationMap.get("organization");
            Designation designation = new Designation(domestic, overseas, organization);

            Animal animal = new Animal().builder()
                    .korName(korName)
                    .engName(engName)
                    .summary(summary)
                    .kind(kind)
                    .image(image)
                    .shape(shape)
                    .ecological(ecological)
                    .introduction(introduction)
                    .distribution(distribution)
                    .effect(effect)
                    .regulate(regulate)
                    .designation(designation)
                    .build();

            dictionaryService.saveDictionary(animal);
        }
    }

    @RequestMapping(method=RequestMethod.GET)
    public List<Dictionary> getDictionaryList() {
        return dictionaryService.getDictionaryList();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Dictionary getDictionary(@PathVariable Long id) {
        Optional<Dictionary> dictionary = dictionaryService.getDictionary(id);
        return dictionary.orElse(null);
    }
}
