package kr.ac.knu.gdsc.Eywa.register.service;

import kr.ac.knu.gdsc.Eywa.dictionary.domain.Dictionary;
import kr.ac.knu.gdsc.Eywa.dictionary.service.DictionaryService;
import kr.ac.knu.gdsc.Eywa.member.domain.Member;
import kr.ac.knu.gdsc.Eywa.register.domain.Register;
import kr.ac.knu.gdsc.Eywa.register.dto.RegisterRequestDto;
import kr.ac.knu.gdsc.Eywa.register.dto.RegisterResponseDto;
import kr.ac.knu.gdsc.Eywa.register.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RegisterService {
    private final DictionaryService dictionaryService;
    private final RegisterRepository registerRepository;

    // 도감 등록
    public void saveRegister(Member member, RegisterRequestDto request) {
        System.out.println(request.getDictionaryId());
        Optional<Dictionary> dictionaryOptional = dictionaryService.getDictionary(request.getDictionaryId());
        if (dictionaryOptional.isEmpty()) {
            return;
        }
        Dictionary dictionary = dictionaryOptional.get();
        Register register = Register.builder()
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .member(member)
                .dictionary(dictionary)
                .build();
        registerRepository.save(register);
    }

    // 도감 등록 목록 조회
    public List<Register> getRegisterList() {
        return registerRepository.findAll();
    }

    public List<Register> getRegisterListByMember(Long memberId) {
        return registerRepository.findByMemberId(memberId);
    }

    public Optional<Register> getRegisterByDictionaryAndMember(Long dictionaryID, Long memberId) {
        return registerRepository.findByDictionaryIdAndMemberId(dictionaryID, memberId);
    }

    public Optional<Register> getRegister(Long registerId) {
        return registerRepository.findById(registerId);
    }
}
