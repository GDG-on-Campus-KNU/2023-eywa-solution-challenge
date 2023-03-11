package kr.ac.knu.gdsc.Eywa.member.respository;

import kr.ac.knu.gdsc.Eywa.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
