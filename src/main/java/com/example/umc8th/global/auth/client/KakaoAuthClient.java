package com.example.umc8th.global.auth.client;

import com.example.umc8th.global.auth.dto.response.OAuthKakaoResDTO;
import com.example.umc8th.global.config.KakaoFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "kakaoAuthClient",
        url = "https://kauth.kakao.com",
        configuration = KakaoFeignConfig.class
)
public interface KakaoAuthClient {

    @PostMapping(value = "/oauth/token")
    OAuthKakaoResDTO.KakaoToken getKakaoToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam("client_id") String clientId,
            @RequestParam("redirect_uri") String redirectURI,
            @RequestParam("code") String code,
            @RequestParam("client_secret") String clientSecret
    );
}

