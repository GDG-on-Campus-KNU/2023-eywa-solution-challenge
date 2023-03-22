package kr.ac.knu.gdsc.Eywa.dictionary.repository;

import kr.ac.knu.gdsc.Eywa.dictionary.domain.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {
    @Override
    Optional<Dictionary> findById(Long id);

    @Override
    List<Dictionary> findAll();
}
