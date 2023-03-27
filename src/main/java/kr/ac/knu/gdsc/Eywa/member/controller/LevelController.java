package kr.ac.knu.gdsc.Eywa.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.knu.gdsc.Eywa.member.domain.Level;
import kr.ac.knu.gdsc.Eywa.member.dto.LevelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LevelController {
    private final LevelService levelService;

    @PostMapping("/level")
    public void createLevel(@RequestBody HashMap<String, Object> request) {
        List<Level> levels = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        for (String s : request.keySet()) {
            int levelNum = Integer.parseInt(s);
            LevelDto levelDto = mapper.convertValue(request.get(s), LevelDto.class);
            Level level = Level.builder()
                    .level(levelNum)
                    .minExp(Integer.parseInt(levelDto.getMinExp()))
                    .maxExp(Integer.parseInt(levelDto.getMaxExp()))
                    .build();
            levels.add(level);
        }
        levelService.saveAll(levels);
    }
}
