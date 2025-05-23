package com.example.umc8th.domain.member.service.command;

import com.example.umc8th.domain.member.dto.OAuth2DTO;
import com.example.umc8th.domain.member.dto.OAuth2RequestDTO;
import com.example.umc8th.domain.member.exception.MemberErrorCode;
import com.example.umc8th.domain.member.exception.MemberException;
import com.example.umc8th.domain.member.repository.MemberRepository;
import com.example.umc8th.global.util.WebClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class KakaoOAuth2Service extends AbstractOAuth2Service {

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String tokenURI;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String userInfoURI;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectURI;

    private final WebClientUtil webClientUtil;

    public KakaoOAuth2Service(MemberRepository memberRepository, TokenCommandService tokenCommandService, WebClientUtil webClientUtil) {
        super(memberRepository, tokenCommandService);
        this.webClientUtil = webClientUtil;
    }

    @Override
    public String getEmail(String accessToken) {
        OAuth2DTO.KakaoProfile profile = getKakaoProfile(accessToken);
        try {
            return profile.getKakao_account().getEmail();
        } catch (NullPointerException e) {
            throw new MemberException(MemberErrorCode.OAUTH_USER_INFO_FAIL);
        }
    }

    @Override
    public String getAccessToken(String accessCode) {
        return getAccessTokenFromKakao(accessCode);
    }

    private String getAccessTokenFromKakao(String accessCode) {
        // 인가코드 토큰 가져오기
        OAuth2DTO.OAuth2TokenDTO tokenDTO = getTokenDTO(accessCode);
        if (tokenDTO == null) {
            throw new MemberException(MemberErrorCode.OAUTH_TOKEN_FAIL);
        }
        return tokenDTO.getAccess_token();
    }

    private OAuth2DTO.KakaoProfile getKakaoProfile(String accessToken) {
        WebClient webClient = webClientUtil.getWebClient(userInfoURI);

        return webClient.get()
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                .retrieve()
                .bodyToMono(OAuth2DTO.KakaoProfile.class)
                .block();
    }

    private OAuth2DTO.OAuth2TokenDTO getTokenDTO(String accessCode) {
        WebClient webClient = webClientUtil.getWebClient(tokenURI);
        OAuth2RequestDTO.OAuth2TokenDTO request = OAuth2RequestDTO.OAuth2TokenDTO.builder()
                .grantType("authorization_code")
                .clientId(clientId)
                .redirectUri(redirectURI)
                .code(accessCode)
                .build();
        return webClient.post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(request.toMap())
                .retrieve()
                .bodyToMono(OAuth2DTO.OAuth2TokenDTO.class)
                .block();
    }
}
