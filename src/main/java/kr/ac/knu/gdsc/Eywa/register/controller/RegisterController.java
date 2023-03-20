package kr.ac.knu.gdsc.Eywa.register.controller;

import kr.ac.knu.gdsc.Eywa.members.domain.Member;
import kr.ac.knu.gdsc.Eywa.register.domain.Register;
import kr.ac.knu.gdsc.Eywa.register.dto.RegisterSaveRequestDto;
import kr.ac.knu.gdsc.Eywa.register.dto.RegisterSaveResponseDto;
import kr.ac.knu.gdsc.Eywa.register.service.RegisterService;
import kr.ac.knu.gdsc.Eywa.members.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/registers")
public class RegisterController {
    private final RegisterService registerService;
    private final MemberService memberService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RegisterSaveResponseDto> register(@ModelAttribute RegisterSaveRequestDto request) {
        RegisterSaveResponseDto response = registerService.save(null, request);
        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Register>> getRegisterList(@AuthenticationPrincipal OAuth2User oAuth2User) {
        Optional<Member> userOptional = memberService.getMember(oAuth2User);
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Register> registers = registerService.findAll(userOptional.get());
        return ResponseEntity.ok().body(registers);
    }
}
