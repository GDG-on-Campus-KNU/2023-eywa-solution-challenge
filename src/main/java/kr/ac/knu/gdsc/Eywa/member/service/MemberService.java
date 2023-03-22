package kr.ac.knu.gdsc.Eywa.member.service;

import kr.ac.knu.gdsc.Eywa.member.domain.GoogleUserInfo;
import kr.ac.knu.gdsc.Eywa.member.domain.Member;
import kr.ac.knu.gdsc.Eywa.auth.PrincipalDetail;
import kr.ac.knu.gdsc.Eywa.member.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> getMember(OAuth2User oAuth2User) {
        return memberRepository.findBySub(oAuth2User.getName());
    }

    public Optional<Member> getMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public Optional<Member> getMemberBySub(String sub) {
        return memberRepository.findBySub(sub);
    }
}
