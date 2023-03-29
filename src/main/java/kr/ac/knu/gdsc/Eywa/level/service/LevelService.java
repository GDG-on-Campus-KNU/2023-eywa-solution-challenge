package kr.ac.knu.gdsc.Eywa.level.service;

import kr.ac.knu.gdsc.Eywa.common.ErrorCode;
import kr.ac.knu.gdsc.Eywa.level.domain.Level;
import kr.ac.knu.gdsc.Eywa.level.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LevelService {
  private final LevelRepository levelRepository;

  // 경험치의 구간에 해당하는 레벨을 반환
  public Level getLevelBetweenExp(int exp) {
    Level level = this.levelRepository
        .findBetweenExp(exp)
        .orElseThrow(() -> new IllegalArgumentException(ErrorCode.LEVEL_NOT_FOUND.getMessage()));
    System.out.println(level.getLevel());
    System.out.println(level.getMinExp());
    System.out.println(level.getMaxExp());
    return level;
  }

  // 레벨의 최대 경험치를 반환
  public int getMaxExpOfLevel(long id) {
    Level level =
        this.levelRepository
            .findById(id)
            .orElseThrow(
                () -> new IllegalArgumentException(ErrorCode.LEVEL_NOT_FOUND.getMessage()));
    return level.getMaxExp();
  }

  public void saveLevel(Level level) {
    this.levelRepository.save(level);
  }
}
