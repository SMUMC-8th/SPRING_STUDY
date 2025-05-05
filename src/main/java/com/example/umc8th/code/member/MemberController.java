package com.example.umc8th.code.member;

import com.example.umc8th.code.member.dto.MemberRequestDTO;
import com.example.umc8th.code.member.dto.MemberResponseDTO;
import com.example.umc8th.code.member.entity.Member;
import com.example.umc8th.code.member.service.MemberCommandService;
import com.example.umc8th.code.member.service.TokenCommandService;
import com.example.umc8th.global.apiPayload.CustomResponse;
import com.example.umc8th.global.jwt.JwtUtil;
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
    private final TokenCommandService tokenCommandService;

    @PostMapping("/sign-up")
    public CustomResponse<MemberResponseDTO.SignUpResponseDTO> signup(@RequestBody MemberRequestDTO.SignUpRequestDTO dto){
        Member member = memberCommandService.signUp(dto);

        return CustomResponse.onSuccess(MemberResponseDTO.SignUpResponseDTO.from(member));
    }


    @PostMapping("/login")
    public CustomResponse<MemberResponseDTO.LoginResponseDTO> login(@RequestBody MemberRequestDTO.LoginRequestDTO dto){
        // 사용자 인증 및 비밀번호 확인

        MemberResponseDTO.LoginResponseDTO dto2 = memberCommandService.login(dto);


        // 응답 포맷에 맞게 반환
        return CustomResponse.onSuccess(dto2);
    }
}
