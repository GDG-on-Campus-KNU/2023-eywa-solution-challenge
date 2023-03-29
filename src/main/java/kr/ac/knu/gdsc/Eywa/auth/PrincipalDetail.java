package kr.ac.knu.gdsc.Eywa.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import kr.ac.knu.gdsc.Eywa.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
@AllArgsConstructor
public class PrincipalDetail implements OAuth2User {
  private Member member;
  private Map<String, Object> attributes;

  @Override
  public String getName() {
    return this.member.getName();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(member.getAuthority());
    return authorities;
  }
}
