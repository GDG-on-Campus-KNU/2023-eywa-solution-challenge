package kr.ac.knu.gdsc.Eywa.level.repository;

import kr.ac.knu.gdsc.Eywa.level.domain.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;

import java.util.List;
import java.util.Optional;

public interface LevelRepository extends JpaRepository<Level, Long> {
    @Override
    Optional<Level> findById(Long id);

    @Query("SELECT level FROM Level level WHERE level.minExp <= :exp AND :exp <= level.maxExp")
    Optional<Level> findBetweenExp(@Param("exp") int exp);
}
