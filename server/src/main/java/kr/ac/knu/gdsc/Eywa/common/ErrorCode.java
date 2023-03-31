package kr.ac.knu.gdsc.Eywa.common;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum ErrorCode {
  LEVEL_NOT_FOUND(404, "Level Not Found", "존재하지 않는 레벨입니다."),
  DICTIONARY_NOT_FOUND(404, "Dictionary Not Found", "존재하지 않는 생태계교란종입니다."),
  REPORT_NOT_FOUND(404, "Report Not Found", "존재하지 않는 신고내역입니다."),
  MEMBER_NOT_FOUND(404, "Member Not Found", "존재하지 않는 회원입니다.");

  private int status;
  private String code;
  private String message;

  ErrorCode(int status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }

  public static ErrorCode valueOfCode(String errorCode) {
    return Arrays.stream(values())
        .filter(value -> value.code.equals(errorCode))
        .findAny()
        .orElse(null);
  }
}
