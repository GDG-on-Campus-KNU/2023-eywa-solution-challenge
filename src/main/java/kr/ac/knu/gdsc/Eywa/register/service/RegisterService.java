package kr.ac.knu.gdsc.Eywa.register.service;

import kr.ac.knu.gdsc.Eywa.dictionary.domain.Dictionary;
import kr.ac.knu.gdsc.Eywa.dictionary.service.DictionaryService;
import kr.ac.knu.gdsc.Eywa.members.domain.Member;
import kr.ac.knu.gdsc.Eywa.members.service.MemberService;
import kr.ac.knu.gdsc.Eywa.register.domain.Register;
import kr.ac.knu.gdsc.Eywa.register.dto.RegisterSaveRequestDto;
import kr.ac.knu.gdsc.Eywa.register.dto.RegisterSaveResponseDto;
import kr.ac.knu.gdsc.Eywa.register.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RegisterService {
    private final DictionaryService dictionaryService;
    private final RegisterRepository registerRepository;

    // 도감 등록
    public RegisterSaveResponseDto saveRegister(Member member, RegisterSaveRequestDto request) {
        System.out.println(request.getDictionaryId());
        Optional<Dictionary> dictionaryOptional = dictionaryService.getDictionary(request.getDictionaryId());
        if (dictionaryOptional.isEmpty()) {
            return null;
        }
        Dictionary dictionary = dictionaryOptional.get();
        Register register = Register.builder()
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .member(member)
                .dictionary(dictionary)
                .build();
        registerRepository.save(register);
        return new RegisterSaveResponseDto(register.getLatitude(), register.getLongitude(), dictionary);
    }

    // 도감 등록 목록 조회
    public List<Register> getAllRegisters() {
        return registerRepository.findAll();
    }

    public List<Register> getRegistersByMember(Long memberId) {
        return registerRepository.findByMemberId(memberId);
    }

    public Optional<Register> getRegisterByDictionaryAndMember(Long dictionaryID, Long memberId) {
        return registerRepository.findByDictionaryIdAndMemberId(dictionaryID, memberId);
    }
}
