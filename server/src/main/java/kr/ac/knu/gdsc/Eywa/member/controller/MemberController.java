package kr.ac.knu.gdsc.Eywa.member.controller;

import javax.servlet.http.HttpServletRequest;
import kr.ac.knu.gdsc.Eywa.auth.PrincipalDetail;
import kr.ac.knu.gdsc.Eywa.member.domain.Member;
import kr.ac.knu.gdsc.Eywa.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/members")
public class MemberController {

  // 본인 정보 조회
  @RequestMapping(method = RequestMethod.GET, value = "/me")
  public ResponseEntity<MemberDto> getMember(
      @AuthenticationPrincipal PrincipalDetail principalDetail) {
    Member member = principalDetail.getMember();
    if (member == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    return ResponseEntity.ok().body(member.toDto());
  }

  // 본인 세션 조회
  @RequestMapping(method = RequestMethod.GET, value = "/session")
  public ResponseEntity<?> getSession(
      HttpServletRequest request, @CookieValue("JSESSIONID") String sessionId) {
    String userAgent = request.getHeader("user-agent");
    boolean isMobile =
        userAgent.matches(
            ".*(iPhone|iPod|iPad|BlackBerry|Android|Windows CE|LG|MOT|SAMSUNG|SonyEricsson|IOS_APP|ANDROID_APP).*");
    HttpHeaders headers = new HttpHeaders();
    if (isMobile) {
      String appLink = "com.googleusercontent.apps.1060976925872-qp92sbr1qjm0peso9ttqa40dgkjpff5t";
      String redirect = appLink + "://?JSESSIONID=" + sessionId;
      headers.add("Location", redirect);
    } else {
      String redirect = "/";
      headers.add("Location", redirect);
    }
    return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).headers(headers).build();
  }
}
