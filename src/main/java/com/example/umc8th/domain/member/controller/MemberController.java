package com.example.umc8th.domain.member.controller;

import com.example.umc8th.domain.member.dto.request.MemberRequestDTO;
import com.example.umc8th.domain.member.dto.response.MemberResponseDTO;
import com.example.umc8th.domain.member.service.command.MemberCommandService;
import com.example.umc8th.domain.member.service.command.OAuth2Service;
import com.example.umc8th.global.apiPayload.CustomResponse;
import com.example.umc8th.global.jwt.dto.JwtDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final OAuth2Service oAuth2Service;

    @PostMapping("/sign-up")
    public CustomResponse<MemberResponseDTO.SignUp> signUp(@RequestBody MemberRequestDTO.SignUp reqDTO) {
        MemberResponseDTO.SignUp resDTO = memberCommandService.signUp(reqDTO);
        return CustomResponse.onSuccess(HttpStatus.CREATED, resDTO);
    }

    @PostMapping("/login")
    public CustomResponse<JwtDTO> login(@RequestBody MemberRequestDTO.login reqDTO) {
        JwtDTO resDTO = memberCommandService.login(reqDTO);
        return CustomResponse.onSuccess(resDTO);
    }

    @GetMapping("/login/oauth2/code/kakao")
    public CustomResponse<JwtDTO> loginWithKakao(@RequestParam("code") String code) {
        JwtDTO jwtDTO = oAuth2Service.loginWithKakao(code);
        return CustomResponse.onSuccess(jwtDTO);
    }
}
