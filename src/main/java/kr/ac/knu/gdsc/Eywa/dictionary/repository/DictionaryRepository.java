package kr.ac.knu.gdsc.Eywa.dictionary.repository;

import kr.ac.knu.gdsc.Eywa.dictionary.domain.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {
    Optional<Dictionary> findById(Long id);
    List<Dictionary> findAll();
}
