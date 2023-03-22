package kr.ac.knu.gdsc.Eywa.member.respository;

import kr.ac.knu.gdsc.Eywa.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findBySub(String sub);
    Optional<Member> findById(Long id);
}
