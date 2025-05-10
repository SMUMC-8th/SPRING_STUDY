package com.example.umc8th.global.auth.client;

import com.example.umc8th.global.auth.dto.response.OAuthKakaoResDTO;
import com.example.umc8th.global.config.KakaoFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "kakaoUserClient",
        url = "https://kapi.kakao.com",
        configuration = KakaoFeignConfig.class
)
public interface KakaoUserClient {

    @GetMapping(value = "/v2/user/me")
    OAuthKakaoResDTO.KakaoUser getKakaoUser(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    );
}
