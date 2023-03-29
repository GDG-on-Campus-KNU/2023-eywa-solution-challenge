package kr.ac.knu.gdsc.Eywa.member.service;

import java.util.Optional;
import kr.ac.knu.gdsc.Eywa.common.ErrorCode;import kr.ac.knu.gdsc.Eywa.level.domain.Level;
import kr.ac.knu.gdsc.Eywa.level.service.LevelService;
import kr.ac.knu.gdsc.Eywa.member.domain.Member;
import kr.ac.knu.gdsc.Eywa.member.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;
  private final LevelService levelService;

  public void saveMember(Member member) {
    int memberExp = member.getExp();
    Level updatedLevel = levelService.getLevelBetweenExp(memberExp);
    member.updateLevel(updatedLevel);
    memberRepository.save(member);
  }

  public Optional<Member> getMemberBySub(String sub) {
    return this.memberRepository.findBySub(sub);
  }

  public Member getMemberById(Long id) {
    return this.memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(ErrorCode.MEMBER_NOT_FOUND.getMessage()));
  }

  public void updateExpOfMember(Member member, int exp) {
    int maxExp = this.levelService.getMaxExpOfLevel(10);
    // 최대 경험치를 넘지 않도록 경험치 갱신
    int updatedExp = Math.min(member.getExp() + exp, maxExp);
    member.addExp(updatedExp);
    // 레벨 갱신
    Level updatedLevel = this.levelService.getLevelBetweenExp(updatedExp);
    member.updateLevel(updatedLevel);
    this.memberRepository.save(member);
  }
}
