package com.example.umc8th.controller;

import com.example.umc8th.dto.MemberRequestDTO;
import com.example.umc8th.dto.MemberResponseDTO;
import com.example.umc8th.entity.Member;
import com.example.umc8th.global.apiPayload.CustomResponse;
import com.example.umc8th.service.command.MemberCommandService;
import com.example.umc8th.service.oauth.OAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final OAuth2Service oAuth2Service;

    @PostMapping("/sign-up")
    public CustomResponse<MemberResponseDTO.SignUpResponseDTO> signUp(@RequestBody MemberRequestDTO.SignUpRequestDTO dto) {
        Member member = memberCommandService.signUp(dto);
        return CustomResponse.ok(MemberResponseDTO.SignUpResponseDTO.from(member));
    }

    @GetMapping("/login")
    public CustomResponse<MemberResponseDTO.LoginResponseDTO> login(@RequestBody MemberRequestDTO.LoginRequestDTO dto) {
        MemberResponseDTO.LoginResponseDTO member = memberCommandService.login(dto);
        return CustomResponse.ok(member);
    }

    @GetMapping("/oauth2/callback/kakao")
    public CustomResponse<MemberResponseDTO.MemberTokenDTO> loginWithKakao(@RequestParam("code") String code) {
        return CustomResponse.ok(oAuth2Service.login(code));
    }
}