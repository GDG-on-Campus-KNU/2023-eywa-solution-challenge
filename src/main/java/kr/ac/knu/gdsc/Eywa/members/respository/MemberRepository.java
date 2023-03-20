package kr.ac.knu.gdsc.Eywa.members.respository;

import kr.ac.knu.gdsc.Eywa.members.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findBySub(String sub);
}
