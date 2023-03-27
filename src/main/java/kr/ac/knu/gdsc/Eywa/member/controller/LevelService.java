package kr.ac.knu.gdsc.Eywa.member.controller;

import kr.ac.knu.gdsc.Eywa.member.domain.Level;
import kr.ac.knu.gdsc.Eywa.member.respository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class LevelService {
    private final LevelRepository levelRepository;

    public void saveAll(Iterable<Level> entities) {
        levelRepository.saveAll(entities);
    }

    public Level findLevelByExp(int exp) {
        //member의 exp -> DB에 저장된 level 중 exp값에 해당하는 level 반환
        levelRepository.findAll().forEach(System.out::println);

        for (Level level : levelRepository.findAll()) {
            if(level.getMinExp() <= exp && exp <= level.getMaxExp()) {
                return level;
            }
        }
        return levelRepository.findById(10L).orElseThrow();
    }
}
