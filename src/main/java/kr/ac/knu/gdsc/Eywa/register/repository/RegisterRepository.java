package kr.ac.knu.gdsc.Eywa.register.repository;


import kr.ac.knu.gdsc.Eywa.register.domain.Register;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegisterRepository extends JpaRepository<Register, Long> {
    @Override
    List<Register> findAll();
}
