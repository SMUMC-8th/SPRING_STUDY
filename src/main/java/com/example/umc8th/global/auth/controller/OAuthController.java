package com.example.umc8th.global.auth.controller;

import com.example.umc8th.domain.member.dto.MemberResponseDTO;
import com.example.umc8th.global.apiPayload.GlobalResponse;
import com.example.umc8th.global.auth.service.command.OAuth2CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth2/callback")
public class OAuthController {

    private final OAuth2CommandService oAuth2CommandService;

    // OAuth2 카카오 리다이렉트
    @GetMapping("/kakao")
    public GlobalResponse<MemberResponseDTO.LoginResponseDTO> loginWithKakao(
            @RequestParam("code") String code
    ) {
         return GlobalResponse.ok(oAuth2CommandService.login(code));
    }
}
