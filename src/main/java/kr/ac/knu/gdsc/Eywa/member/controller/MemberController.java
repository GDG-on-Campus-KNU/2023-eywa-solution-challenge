package kr.ac.knu.gdsc.Eywa.member.controller;

import kr.ac.knu.gdsc.Eywa.auth.PrincipalDetail;
import kr.ac.knu.gdsc.Eywa.auth.PrincipalDetailService;
import kr.ac.knu.gdsc.Eywa.member.domain.Member;
import kr.ac.knu.gdsc.Eywa.member.dto.MemberDto;
import kr.ac.knu.gdsc.Eywa.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/members")
public class MemberController {

    private final LevelService levelService;
    private final PrincipalDetailService principalDetailService;
    private final MemberService memberService;

    @RequestMapping(method = RequestMethod.GET, value="/me")
    public ResponseEntity<MemberDto> getMember(@AuthenticationPrincipal PrincipalDetail oAuth2User) {

        Member member = principalDetailService.getMemberBySub(oAuth2User.getMember().getSub());
        memberService.saveMember(member);
        return ResponseEntity.ok().body(member.toDto());
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
