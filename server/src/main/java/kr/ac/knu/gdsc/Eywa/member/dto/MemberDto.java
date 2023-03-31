package kr.ac.knu.gdsc.Eywa.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDto {
  private String name;
  private String email;
  private int exp;
  private String picture;
  private int level;
}
