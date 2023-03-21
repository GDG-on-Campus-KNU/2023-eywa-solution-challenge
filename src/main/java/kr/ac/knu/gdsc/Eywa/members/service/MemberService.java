package kr.ac.knu.gdsc.Eywa.members.service;

import kr.ac.knu.gdsc.Eywa.members.domain.GoogleUserInfo;
import kr.ac.knu.gdsc.Eywa.members.domain.Member;
import kr.ac.knu.gdsc.Eywa.members.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);
        GoogleUserInfo googleUserInfo = new GoogleUserInfo(oauth2User.getAttributes());
        Optional<Member> userOptional = memberRepository.findBySub(googleUserInfo.getSub());
        Member member;
        if (userOptional.isEmpty()) {
            member = Member.builder()
                    .sub(googleUserInfo.getSub())
                    .name(googleUserInfo.getName())
                    .picture(googleUserInfo.getPicture())
                    .email(googleUserInfo.getEmail())
                    .role("USER")
                    .build();
        } else {
            member = userOptional.get();
            member.setEmail(googleUserInfo.getEmail());
        }
        memberRepository.save(member);
        return oauth2User;
    }

    public Optional<Member> getMember(OAuth2User oAuth2User) {
        return memberRepository.findBySub(oAuth2User.getName());
    }

    public Optional<Member> getMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
