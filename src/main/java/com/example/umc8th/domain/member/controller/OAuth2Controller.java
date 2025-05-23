package com.example.umc8th.domain.member.controller;

import com.example.umc8th.domain.member.dto.MemberResponseDTO;
import com.example.umc8th.domain.member.service.command.OAuth2Service;
import com.example.umc8th.global.apiPayload.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth2")
public class OAuth2Controller {

    private final OAuth2Service webClientOAuthService;
    private final OAuth2Service kakaoOAuth2Service;

    @GetMapping("/callback/{provider}")
    public CustomResponse<MemberResponseDTO.LoginResponseDTO> loginWithKakao(@PathVariable String provider,
                                                                             @RequestParam("code") String code) {
        return CustomResponse.ok(webClientOAuthService.login(provider, code));
    }
}
