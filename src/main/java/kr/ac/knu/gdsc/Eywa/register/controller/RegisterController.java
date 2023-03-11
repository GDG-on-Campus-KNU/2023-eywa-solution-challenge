package kr.ac.knu.gdsc.Eywa.register.controller;

import kr.ac.knu.gdsc.Eywa.member.domain.Member;
import kr.ac.knu.gdsc.Eywa.register.domain.Register;
import kr.ac.knu.gdsc.Eywa.register.dto.RegisterSaveRequestDto;
import kr.ac.knu.gdsc.Eywa.register.dto.RegisterSaveResponseDto;
import kr.ac.knu.gdsc.Eywa.register.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RegisterController {
    private final RegisterService registerService;

    @PostMapping("/registers")
    public ResponseEntity<RegisterSaveResponseDto> register(@ModelAttribute RegisterSaveRequestDto request) {
        RegisterSaveResponseDto response = registerService.save(null, request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/registers")
    public ResponseEntity<List<Register>> findAll(@AuthenticationPrincipal Member member) {
        List<Register> registers = registerService.findAll(member);
        return ResponseEntity.ok().body(registers);
    }

}
