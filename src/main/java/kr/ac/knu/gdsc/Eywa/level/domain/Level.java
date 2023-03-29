package kr.ac.knu.gdsc.Eywa.level.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Level {
  @Id
  @GeneratedValue
  @Column(name = "level_id")
  private Long id;

  @NotNull private int level;
  @NotNull private int maxExp;
  @NotNull private int minExp;

  @Builder
  public Level(int level, int maxExp, int minExp) {
    this.level = level;
    this.maxExp = maxExp;
    this.minExp = minExp;
  }
}
