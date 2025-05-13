package com.example.umc8th.domain.member.controller;

import com.example.umc8th.domain.member.dto.response.MemberResponseDTO;
import com.example.umc8th.domain.member.service.command.OAuth2Service;
import com.example.umc8th.global.apiPayload.CustomResponse;
import com.example.umc8th.global.apiPayload.success.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    @GetMapping("/oauth2/callback/kakao")
    public CustomResponse<MemberResponseDTO.MemberTokenDTO> loginWithKakao(@RequestParam("code") String code) {
        MemberResponseDTO.MemberTokenDTO tokenDTO = oAuth2Service.login(code);

        return CustomResponse.onSuccess(GeneralSuccessCode.OK, tokenDTO); // 표준 응답 포맷으로 반환
    }
}