package kr.ac.knu.gdsc.Eywa.member.service;

import kr.ac.knu.gdsc.Eywa.member.controller.LevelService;
import kr.ac.knu.gdsc.Eywa.member.domain.Level;
import kr.ac.knu.gdsc.Eywa.member.domain.Member;
import kr.ac.knu.gdsc.Eywa.member.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final LevelService levelService;

    public Member saveMember(Member member) {

        Level level= levelService.findLevelByExp(member.getExp());
        member.updateLevel(level);
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

    public void updateExpById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        member.addExp(10);
        Level level = levelService.findLevelByExp(member.getExp());
        member.updateLevel(level);
        memberRepository.save(member);
    }
}
