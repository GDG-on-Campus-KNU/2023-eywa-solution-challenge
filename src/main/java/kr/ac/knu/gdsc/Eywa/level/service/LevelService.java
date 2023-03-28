package kr.ac.knu.gdsc.Eywa.level.service;

import kr.ac.knu.gdsc.Eywa.level.domain.Level;
import kr.ac.knu.gdsc.Eywa.level.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LevelService {
    private final LevelRepository levelRepository;

    // 경험치의 구간에 해당하는 레벨을 반환
    public Optional<Level> getLevelBetweenExp(int exp) {
        return levelRepository.findBetweenExp(exp);
    }

    // 레벨의 최대 경험치를 반환
    public Optional<Integer> getMaxExpOfLevel(long level){
        Optional<Level> levelOptional = levelRepository.findById(level);
        return levelOptional.map(Level::getMaxExp).or(Optional::empty);
    }

    public void saveLevel(Level level) {
        levelRepository.save(level);
    }
}
