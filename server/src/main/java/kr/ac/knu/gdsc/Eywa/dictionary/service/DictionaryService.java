package kr.ac.knu.gdsc.Eywa.dictionary.service;

import java.util.List;
import kr.ac.knu.gdsc.Eywa.common.ErrorCode;
import kr.ac.knu.gdsc.Eywa.dictionary.domain.Dictionary;
import kr.ac.knu.gdsc.Eywa.dictionary.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService {
  private final DictionaryRepository dictionaryRepository;

  @Autowired
  public DictionaryService(DictionaryRepository animalRepository) {
    this.dictionaryRepository = animalRepository;
  }

  public Dictionary getDictionary(Long id) {
    return dictionaryRepository
        .findById(id)
        .orElseThrow(
            () -> new IllegalArgumentException(ErrorCode.DICTIONARY_NOT_FOUND.getMessage()));
  }

  public List<Dictionary> getDictionaryList() {
    return dictionaryRepository.findAll();
  }

  public void saveDictionary(Dictionary dictionary) {
    dictionaryRepository.save(dictionary);
  }
}
