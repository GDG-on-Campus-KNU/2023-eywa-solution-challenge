package kr.ac.knu.gdsc.Eywa.member.controller;

import kr.ac.knu.gdsc.Eywa.member.domain.Member;
import kr.ac.knu.gdsc.Eywa.auth.PrincipalDetail;
import kr.ac.knu.gdsc.Eywa.member.dto.MemberDto;
import kr.ac.knu.gdsc.Eywa.member.service.MemberService;
import kr.ac.knu.gdsc.Eywa.register.domain.Register;
import kr.ac.knu.gdsc.Eywa.report.domain.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/members")
public class MemberController {
    private final MemberService memberService;

    @RequestMapping(method = RequestMethod.GET, value="/me")
    public ResponseEntity<MemberDto> getMember(@AuthenticationPrincipal PrincipalDetail oAuth2User) {
        Member member = oAuth2User.getMember();
        return ResponseEntity.ok().body(member.toDto());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/reports")
    public ResponseEntity<List<Report>> getReports(@AuthenticationPrincipal PrincipalDetail oAuth2User) {
        Member member = oAuth2User.getMember();
        return ResponseEntity.ok().body(member.getReports());
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
