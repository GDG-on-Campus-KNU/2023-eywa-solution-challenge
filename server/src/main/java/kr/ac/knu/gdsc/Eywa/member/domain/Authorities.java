package kr.ac.knu.gdsc.Eywa.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
public enum Authorities implements GrantedAuthority {
  ADMIN(ROLES.ADMIN, "관리자"),
  USER(ROLES.USER, "사용자");

  private final String authority;
  private final String description;

  public static class ROLES {
    public static final String USER = "ROLE_USER";
    public static final String ADMIN = "ROLE_ADMIN";
  }
}
