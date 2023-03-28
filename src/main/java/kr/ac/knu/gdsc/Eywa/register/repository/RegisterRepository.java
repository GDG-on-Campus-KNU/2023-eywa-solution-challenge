package kr.ac.knu.gdsc.Eywa.register.repository;

import kr.ac.knu.gdsc.Eywa.register.domain.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface RegisterRepository extends JpaRepository<Register, Long> {
    @Override
    List<Register> findAll();
    List<Register> findByMemberId(Long memberId);
    Optional<Register> findById(Long registerId);
}
