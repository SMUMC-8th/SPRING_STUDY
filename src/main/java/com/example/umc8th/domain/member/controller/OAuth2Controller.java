package com.example.umc8th.domain.member.controller;

import com.example.umc8th.domain.member.service.command.OAuth2Service;
import com.example.umc8th.global.apiPayload.CustomResponse;
import com.example.umc8th.global.jwt.dto.JwtDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    @GetMapping("/oauth2/callback/kakao")
    public CustomResponse<JwtDTO> loginWithKakao(@RequestParam("code") String code) {
        JwtDTO jwtDTO = oAuth2Service.loginWithKakao(code);
        return CustomResponse.onSuccess(jwtDTO);
    }
}
