package kr.ac.knu.gdsc.Eywa.register.controller;

import kr.ac.knu.gdsc.Eywa.auth.PrincipalDetail;
import kr.ac.knu.gdsc.Eywa.member.domain.Authorities;
import kr.ac.knu.gdsc.Eywa.member.domain.Member;
import kr.ac.knu.gdsc.Eywa.member.service.MemberService;
import kr.ac.knu.gdsc.Eywa.register.domain.Register;
import kr.ac.knu.gdsc.Eywa.register.dto.RegisterRequestDto;
import kr.ac.knu.gdsc.Eywa.register.dto.RegisterResponseDto;
import kr.ac.knu.gdsc.Eywa.register.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/registers")
public class RegisterController {
    private final RegisterService registerService;
    private final MemberService memberService;

    // 도감 본인 기록 조회
    @Secured(Authorities.ROLES.USER)
    @RequestMapping(method = RequestMethod.GET, value = "/members/me")
    public ResponseEntity<List<RegisterResponseDto>> getMyRegisterList(@AuthenticationPrincipal PrincipalDetail oAuth2User) {
        Member member = oAuth2User.getMember();
        List<Register> registerList = this.registerService.getRegisterListByMember(member.getId());
        List<RegisterResponseDto> registerResponseDtoList = new ArrayList<>();
        registerList.forEach(register -> {
            registerResponseDtoList.add(register.toDto());
        });
        return ResponseEntity.ok().body(registerResponseDtoList);
    }

    // 도감 전체 기록 조회
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<RegisterResponseDto>> getRegisterList() {
        List<Register> registerList = this.registerService.getRegisterList();
        List<RegisterResponseDto> registerResponseDtoList = new ArrayList<>();
        registerList.forEach(register -> {
            registerResponseDtoList.add(register.toDto());
        });
        return ResponseEntity.ok().body(registerResponseDtoList);
    }

    // 도감 기록
    @Secured(Authorities.ROLES.USER)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto request, @AuthenticationPrincipal PrincipalDetail oAuth2User) {
        Long dictionaryId = request.getDictionaryId();
        Member member = oAuth2User.getMember();
//        // 도감 기록 여부 확인
//        Optional<Register> registerOptional = registerService.getRegisterByDictionaryAndMember(dictionaryId, member.getId());
//        if (registerOptional.isPresent()) {
//            return ResponseEntity.badRequest().build();
//        }
        // 도감 기록
        registerService.saveRegister(member, request);

        //도감 등록 시 경험치 상승
        memberService.updateExpById(member.getId());

        return ResponseEntity.ok().build();
    }
}
