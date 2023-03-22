package kr.ac.knu.gdsc.Eywa.register.controller;

import kr.ac.knu.gdsc.Eywa.member.domain.Member;
import kr.ac.knu.gdsc.Eywa.auth.PrincipalDetail;
import kr.ac.knu.gdsc.Eywa.register.domain.Register;
import kr.ac.knu.gdsc.Eywa.register.dto.RegisterRequestDto;
import kr.ac.knu.gdsc.Eywa.register.dto.RegisterResponseDto;
import kr.ac.knu.gdsc.Eywa.register.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/registers")
public class RegisterController {
    private final RegisterService registerService;

    // 도감 기록
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto request, @AuthenticationPrincipal PrincipalDetail oAuth2User) {
        Long dictionaryId = request.getDictionaryId();
        Member member = oAuth2User.getMember();
        // 도감 기록 여부 확인
        Optional<Register> registerOptional = registerService.getRegisterByDictionaryAndMember(dictionaryId, member.getId());
        if (registerOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        // 도감 기록
        registerService.saveRegister(member, request);
        return ResponseEntity.ok().build();
    }
}
