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
    public ResponseEntity<RegisterSaveResponseDto> register(@RequestBody RegisterSaveRequestDto request, @AuthenticationPrincipal OAuth2User oAuth2User) {
        Long dictionaryId = request.getDictionaryId();
        Optional<Member> memberOptional = memberService.getMember(oAuth2User);
        if (memberOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Member member = memberOptional.get();
        Optional<Register> registerOptional = registerService.getRegisterByDictionaryAndMember(dictionaryId, member.getId());
        if (registerOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        RegisterSaveResponseDto response = registerService.saveRegister(member, request);
        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Register>> getAllRegisters() {
        List<Register> registers = registerService.getAllRegisters();
        return ResponseEntity.ok().body(registers);
    }

    @RequestMapping(method = RequestMethod.GET, value="/{memberId}")
    public ResponseEntity<List<Register>> getRegistersByMember(@PathVariable String memberId) {
        Optional<Member> memberOptional = memberService.getMember(Long.valueOf(memberId));
        if (memberOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Register> registers = registerService.getRegistersByMember(Long.valueOf(memberId));
        return ResponseEntity.ok().body(registers);
    }
}
