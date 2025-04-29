package com.example.umc8th.code.member;

import com.example.umc8th.global.apiPayload.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/sign-up")
    public CustomResponse<MemberResponseDTO.SignUpResponseDTO> signup(@RequestBody MemberRequestDTO.SignUpRequestDTO dto){
        Member member = memberCommandService.signUp(dto);

        return CustomResponse.onSuccess(MemberResponseDTO.SignUpResponseDTO.from(member));
    }
}
