package kr.ac.knu.gdsc.Eywa.auth;

import java.util.Optional;
import kr.ac.knu.gdsc.Eywa.member.domain.Authorities;
import kr.ac.knu.gdsc.Eywa.member.domain.GoogleUserInfo;
import kr.ac.knu.gdsc.Eywa.member.domain.Member;
import kr.ac.knu.gdsc.Eywa.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService extends DefaultOAuth2UserService {
  private final MemberService memberService;

  // 로그인 처리
  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);
    GoogleUserInfo googleUserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
    Optional<Member> memberOptional = memberService.getMemberBySub(googleUserInfo.getSub());
    Member member;
    if (memberOptional.isEmpty()) { // 회원 가입 하지 않은 경우 자동 회원 가입
      member =
          Member.builder()
              .sub(googleUserInfo.getSub())
              .name(googleUserInfo.getName())
              .picture(googleUserInfo.getPicture())
              .email(googleUserInfo.getEmail())
              .authority(Authorities.USER)
              .build();
    } else { // 회원 가입한 경우 이메일 갱신
      member = memberOptional.get();
      member.updateEmail(googleUserInfo.getEmail());
    }
    memberService.saveMember(member);
    return new PrincipalDetail(member, oAuth2User.getAttributes());
  }
}
