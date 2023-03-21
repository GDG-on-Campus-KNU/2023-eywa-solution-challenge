package kr.ac.knu.gdsc.Eywa.members.controller;

import kr.ac.knu.gdsc.Eywa.members.domain.Member;
import kr.ac.knu.gdsc.Eywa.members.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/members")
public class MembersController {
    private final MemberService memberService;

    @RequestMapping(method = RequestMethod.GET)
    public Member getMember(@AuthenticationPrincipal OAuth2User oAuth2User) {
        Optional<Member> memberOptional = this.memberService.getMember(oAuth2User);
        return memberOptional.orElse(null);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/session")
    public ResponseEntity<?> getSession(HttpServletRequest request, @CookieValue("JSESSIONID") String sessionId) {
        String userAgent = request.getHeader("user-agent");
        boolean isMobile = userAgent.matches(".*(iPhone|iPod|iPad|BlackBerry|Android|Windows CE|LG|MOT|SAMSUNG|SonyEricsson|IOS_APP|ANDROID_APP).*");
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
