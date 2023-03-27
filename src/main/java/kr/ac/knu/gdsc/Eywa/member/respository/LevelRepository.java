package kr.ac.knu.gdsc.Eywa.member.respository;

import kr.ac.knu.gdsc.Eywa.member.domain.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LevelRepository extends JpaRepository<Level, Long> {
    @Override
    <S extends Level> List<S> saveAll(Iterable<S> entities);

}
