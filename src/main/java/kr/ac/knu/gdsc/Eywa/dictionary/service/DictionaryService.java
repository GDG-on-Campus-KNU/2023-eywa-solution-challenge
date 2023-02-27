package kr.ac.knu.gdsc.Eywa.dictionary.service;

import kr.ac.knu.gdsc.Eywa.dictionary.domain.Dictionary;
import kr.ac.knu.gdsc.Eywa.dictionary.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DictionaryService {
    private final DictionaryRepository dictionaryRepository;

    @Autowired
    public DictionaryService(DictionaryRepository animalRepository) {
        this.dictionaryRepository = animalRepository;
    }

    public Optional<Dictionary> getDictionary(Long id) {
        return dictionaryRepository.findById(id);
    }

    public List<Dictionary> getDictionaryList() {
        return dictionaryRepository.findAll();
    }

    public void saveDictionary(Dictionary dictionary) {
        dictionaryRepository.save(dictionary);
    }
}
