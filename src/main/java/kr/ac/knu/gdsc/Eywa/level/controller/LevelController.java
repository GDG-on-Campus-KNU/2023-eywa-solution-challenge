package kr.ac.knu.gdsc.Eywa.level.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import kr.ac.knu.gdsc.Eywa.level.domain.Level;
import kr.ac.knu.gdsc.Eywa.level.dto.LevelDto;
import kr.ac.knu.gdsc.Eywa.level.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/level")
public class LevelController {
  private final LevelService levelService;

  @PostMapping()
  public void createLevel(@RequestBody HashMap<String, Object> request) {
    ObjectMapper mapper = new ObjectMapper();
    LevelDto levelDto = mapper.convertValue(request, LevelDto.class);
    Level level =
        Level.builder()
            .level(levelDto.getLevel())
            .minExp(levelDto.getMinExp())
            .maxExp(levelDto.getMaxExp())
            .build();
    levelService.saveLevel(level);
  }
}
