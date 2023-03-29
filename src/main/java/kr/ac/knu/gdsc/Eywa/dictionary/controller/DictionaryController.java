package kr.ac.knu.gdsc.Eywa.dictionary.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kr.ac.knu.gdsc.Eywa.auth.PrincipalDetail;
import kr.ac.knu.gdsc.Eywa.dictionary.domain.Dictionary;
import kr.ac.knu.gdsc.Eywa.dictionary.domain.animal.*;
import kr.ac.knu.gdsc.Eywa.dictionary.domain.plant.Plant;
import kr.ac.knu.gdsc.Eywa.dictionary.domain.plant.PlantEcological;
import kr.ac.knu.gdsc.Eywa.dictionary.domain.plant.PlantIntroduction;
import kr.ac.knu.gdsc.Eywa.dictionary.domain.plant.Shape;
import kr.ac.knu.gdsc.Eywa.dictionary.dto.DictionaryDto;
import kr.ac.knu.gdsc.Eywa.dictionary.service.DictionaryService;
import kr.ac.knu.gdsc.Eywa.member.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/dictionary")
public class DictionaryController {
  private final DictionaryService dictionaryService;

  @Autowired
  public DictionaryController(DictionaryService dictionaryService) {
    this.dictionaryService = dictionaryService;
  }

  // 도감 등록
  @RequestMapping(method = RequestMethod.POST)
  public void addDictionary(@RequestBody HashMap<String, Object> dictionary) {
    String korName = (String) dictionary.get("korean_name");
    String engName = (String) dictionary.get("english_name");
    String summary = (String) dictionary.get("summary");
    String kind = (String) dictionary.get("kind");
    String image = (String) dictionary.get("image");

    if (kind.equals("plant")) {
      HashMap<String, Object> shapeMap = (HashMap<String, Object>) dictionary.get("shape");
      HashMap<String, Object> flowerMap = (HashMap<String, Object>) shapeMap.get("flower");
      String flowerDescription = (String) flowerMap.get("description");
      String flowerColor = (String) flowerMap.get("color");
      String size = (String) shapeMap.get("size");
      String stem = (String) shapeMap.get("stem");
      String leaf = (String) shapeMap.get("leaf");
      String fruit = (String) shapeMap.get("fruit");
      Shape shape = new Shape(size, stem, leaf, flowerDescription, flowerColor, fruit);

      HashMap<String, Object> ecologicalMap =
          (HashMap<String, Object>) dictionary.get("ecological");
      HashMap<String, Object> habitat = (HashMap<String, Object>) ecologicalMap.get("habitat");
      String habitatDomestic = (String) habitat.get("domestic");
      String habitatOverseas = (String) habitat.get("overseas");
      String growthPeriod = (String) ecologicalMap.get("growth_period");
      String bloomPeriod = (String) ecologicalMap.get("bloom_period");
      PlantEcological ecological =
          new PlantEcological(growthPeriod, bloomPeriod, habitatDomestic, habitatOverseas);

      HashMap<String, Object> introductionMap =
          (HashMap<String, Object>) dictionary.get("introduction");
      String origin = (String) introductionMap.get("origin");
      String period = (String) introductionMap.get("period");
      PlantIntroduction introduction = new PlantIntroduction(origin, period);

      Plant plant =
          Plant.builder()
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
      String shape = (String) dictionary.get("shape");

      HashMap<String, Object> ecologicalMap =
          (HashMap<String, Object>) dictionary.get("ecological");
      String habitat = (String) ecologicalMap.get("habitat");
      String lifespan = (String) ecologicalMap.get("lifespan");
      String etc = (String) ecologicalMap.get("etc");
      AnimalEcological ecological = new AnimalEcological(habitat, lifespan, etc);

      HashMap<String, Object> introductionMap =
          (HashMap<String, Object>) dictionary.get("introduction");
      String origin = (String) introductionMap.get("origin");
      String period = (String) introductionMap.get("period");
      String purpose = (String) introductionMap.get("purpose");
      AnimalIntroduction introduction = new AnimalIntroduction(origin, period, purpose);

      String distribution = (String) dictionary.get("distribution");

      HashMap<String, Object> effectMap = (HashMap<String, Object>) dictionary.get("effect");
      String ecosystem = (String) effectMap.get("ecosystem");
      String entity = (String) effectMap.get("entity");
      Effect effect = new Effect(ecosystem, entity);

      HashMap<String, Object> regulateMap = (HashMap<String, Object>) dictionary.get("regulate");
      String past = (String) regulateMap.get("past");
      String reason = (String) regulateMap.get("reason");
      String method = (String) regulateMap.get("method");
      Regulate regulate = new Regulate(past, reason, method);

      HashMap<String, Object> designationMap =
          (HashMap<String, Object>) dictionary.get("designation");
      String domestic = (String) designationMap.get("domestic");
      String overseas = (String) designationMap.get("overseas");
      String organization = (String) designationMap.get("organization");
      Designation designation = new Designation(domestic, overseas, organization);

      Animal animal =
          Animal.builder()
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

  // 도감 목록 조회
  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<DictionaryDto>> getDictionaryList(
      @AuthenticationPrincipal PrincipalDetail oAuth2User) {
    List<DictionaryDto> dictionaryList = new ArrayList<>();
    if (oAuth2User == null) {
      dictionaryService
          .getDictionaryList()
          .forEach(
              dictionary -> {
                dictionaryList.add(dictionary.toDto());
              });
    } else { // 로그인 한 경우, 도감 기록 여부 포함
      Member member = oAuth2User.getMember();
      dictionaryService
          .getDictionaryList()
          .forEach(
              dictionary -> {
                dictionaryList.add(dictionary.toDto(member.getId()));
              });
    }
    if (dictionaryList.isEmpty()) {
      return ResponseEntity.badRequest().build();
    } else {
      return ResponseEntity.ok(dictionaryList);
    }
  }

  // 도감 상세 조회
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<DictionaryDto> getDictionary(
      @AuthenticationPrincipal PrincipalDetail oAuth2User, @PathVariable Long id) {
    Dictionary dictionary = dictionaryService.getDictionary(id);
    if (oAuth2User == null) {
      return ResponseEntity.ok(dictionary.toDto());
    } else { // 로그인 한 경우, 도감 기록 여부 포함
      return ResponseEntity.ok(dictionary.toDto(oAuth2User.getMember().getId()));
    }
  }
}
