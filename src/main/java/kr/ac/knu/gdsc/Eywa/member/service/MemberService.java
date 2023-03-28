package kr.ac.knu.gdsc.Eywa.member.service;

import kr.ac.knu.gdsc.Eywa.level.domain.Level;
import kr.ac.knu.gdsc.Eywa.level.service.LevelService;
import kr.ac.knu.gdsc.Eywa.member.domain.Member;
import kr.ac.knu.gdsc.Eywa.member.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final LevelService levelService;

    public void saveMember(Member member) {
        int memberExp = member.getExp();
        Level updatedLevel = levelService.getLevelBetweenExp(memberExp).orElseThrow(() -> new IllegalArgumentException(String.format("경험치 구간 %d에 해당하는 레벨이 존재하지 않습니다.", memberExp)));
        member.updateLevel(updatedLevel);
        memberRepository.save(member);
    }

    public Optional<Member> getMemberBySub(String sub) {
        return memberRepository.findBySub(sub);
    }

    public void updateExpOfMember(Long memberId, int exp) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException(String.format("%d에 해당하는 사용자가 존재하지 않습니다.", memberId)));
        int maxExp = levelService.getMaxExpOfLevel(10).orElseThrow(() -> new IllegalArgumentException(String.format("레벨 10의 최대 경험치가 설정되지 않았습니다.")));
        // 최대 레벨이 아닌 경우 경험치 갱신
        if (member.getExp() + exp <= maxExp) {
            member.addExp(exp);
        }
        // 레벨업 처리
        int memberExp = member.getExp();
        Level updatedLevel = levelService.getLevelBetweenExp(memberExp).orElseThrow(() -> new IllegalArgumentException(String.format("경험치 구간 %d에 해당하는 레벨이 존재하지 않습니다.", memberExp)));
        member.updateLevel(updatedLevel);
        memberRepository.save(member);
    }
}
