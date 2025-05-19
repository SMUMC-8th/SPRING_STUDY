package com.example.umc8th.global.kakao;

import com.example.umc8th.code.member.dto.MemberResponseDTO;

import com.example.umc8th.global.apiPayload.CustomResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    @GetMapping("/oauth2/callback/kakao")
    // queryParam 형식으로 코드를 받을 예정이니 RequestParam을 설정해줍니다
    // 응답은 저희 서버에 로그인 다 한 뒤에 토큰을 제공할 예정이니 TokenDTO로 설정해줍니다.
    public CustomResponse<MemberResponseDTO.LoginResponseDTO> loginWithKakao(
            @RequestParam("code") String code) {
        log.info("[kakao]: {}", code);
        return CustomResponse.onSuccess(oAuth2Service.login(code));
    }
}
