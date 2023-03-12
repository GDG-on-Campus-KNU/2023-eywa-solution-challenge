package kr.ac.knu.gdsc.Eywa.register.service;

import kr.ac.knu.gdsc.Eywa.dictionary.domain.Dictionary;
import kr.ac.knu.gdsc.Eywa.dictionary.repository.DictionaryRepository;
import kr.ac.knu.gdsc.Eywa.member.domain.Member;
import kr.ac.knu.gdsc.Eywa.member.respository.MemberRepository;
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
    private final MemberRepository memberRepository;
    private final RegisterRepository registerRepository;
    private final DictionaryRepository dictionaryRepository;

    /**
     * 도감 등록
     */
    public RegisterSaveResponseDto save(Member member, RegisterSaveRequestDto request) {
        Dictionary dictionary = dictionaryRepository.findById(request.getDictionaryId()).get();

        // TODO: 회원 정보 추가
        Register register = Register.builder()
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build();
        registerRepository.save(register);

        return new RegisterSaveResponseDto(register.getLatitude(), register.getLongitude(), dictionary);
    }

    /**
     * 도감 등록 목록 조회
     */
    public List<Register> findAll(Member member) {
        return registerRepository.findAll();
    }






}
