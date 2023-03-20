package kr.ac.knu.gdsc.Eywa.members.controller;

import kr.ac.knu.gdsc.Eywa.members.domain.Member;
import kr.ac.knu.gdsc.Eywa.members.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MembersController {
    private final MemberService memberService;

    @RequestMapping(method = RequestMethod.GET)
    public Member getMember(@AuthenticationPrincipal OAuth2User oAuth2User) {
        Optional<Member> memberOptional = this.memberService.getMember(oAuth2User);
        return memberOptional.orElse(null);
    }
}
