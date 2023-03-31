package kr.ac.knu.gdsc.Eywa.member.respository;

import java.util.Optional;
import kr.ac.knu.gdsc.Eywa.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
  Optional<Member> findBySub(String sub);

  @Override
  Optional<Member> findById(Long id);
}
