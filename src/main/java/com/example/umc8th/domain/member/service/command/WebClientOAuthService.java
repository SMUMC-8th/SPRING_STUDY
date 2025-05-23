package com.example.umc8th.domain.member.service.command;

import com.example.umc8th.domain.member.dto.MemberResponseDTO;
import com.example.umc8th.domain.member.dto.OAuth2DTO;
import com.example.umc8th.domain.member.dto.OAuth2RequestDTO;
import com.example.umc8th.domain.member.entity.Member;
import com.example.umc8th.domain.member.enums.SocialType;
import com.example.umc8th.domain.member.exception.MemberErrorCode;
import com.example.umc8th.domain.member.exception.MemberException;
import com.example.umc8th.domain.member.repository.MemberRepository;
import com.example.umc8th.global.util.WebClientUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WebClientOAuthService implements OAuth2Service {

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String tokenURI;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String userInfoURI;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectURI;

    private final MemberRepository memberRepository;
    private final TokenCommandService tokenCommandService;
    private final WebClientUtil webClientUtil;

    @Override
    public MemberResponseDTO.LoginResponseDTO login(String provider, String code) {
        if (provider.equalsIgnoreCase(SocialType.KAKAO.name())) {
            return loginWithKakao(code);
        }
        else {
            throw new MemberException(MemberErrorCode.UNSUPPORTED_OAUTH_TYPE);
        }
    }

    private MemberResponseDTO.LoginResponseDTO loginWithKakao(String code) {
        String token = getAccessTokenFromKakao(code);
        OAuth2DTO.KakaoProfile profile = getProfileFromKakao(token);
        String email = profile.getKakao_account().getEmail();
        return loginOrSignup(SocialType.KAKAO, email);
    }

    private MemberResponseDTO.LoginResponseDTO loginOrSignup(SocialType socialType, String email) {
        // SocialType을 Member에 provider라는 필드로 추가해서 저장해도 좋음
        Member member;
        Optional<Member> optional = memberRepository.findByEmail(email);
        member = optional.orElseGet(() -> memberRepository.save(Member.builder()
                .email(email)
                .password("")
                .socialType(socialType)
                .role("ROLE_USER")
                .build()));


        return tokenCommandService.createLoginToken(member);
    }

    private String getAccessTokenFromKakao(String accessCode) {
        // 인가코드 토큰 가져오기
        WebClient webClient = webClientUtil.getWebClient(tokenURI);
        OAuth2RequestDTO.OAuth2TokenDTO request = OAuth2RequestDTO.OAuth2TokenDTO.builder()
                .grantType("authorization_code")
                .clientId(clientId)
                .redirectUri(redirectURI)
                .code(accessCode)
                .build();
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add("grant_type", "authorization_code");
//        map.add("client_id", clientId);
//        map.add("redirect_uri", redirectURI);
//        map.add("code", accessCode);
        OAuth2DTO.OAuth2TokenDTO tokenDTO = webClient.post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(request.toMap())
                .retrieve()
                .bodyToMono(OAuth2DTO.OAuth2TokenDTO.class)
                .block();
        if (tokenDTO == null) {
            throw new MemberException(MemberErrorCode.OAUTH_TOKEN_FAIL);
        }
        return tokenDTO.getAccess_token();
    }
    private OAuth2DTO.KakaoProfile getProfileFromKakao(String accessToken) {
        WebClient webClient = webClientUtil.getWebClient(userInfoURI);

        return webClient.get()
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                .retrieve()
                .bodyToMono(OAuth2DTO.KakaoProfile.class)
                .block();

//        httpHeaders.add("Authorization", "Bearer " + accessToken);
//        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

//        HttpEntity<MultiValueMap> request1 = new HttpEntity<>(httpHeaders);
//
//        ResponseEntity<String> response2 = restTemplate.exchange(
//                userInfoURI,
//                HttpMethod.GET,
//                request1,
//                String.class
//        );
//
//        ObjectMapper om = new ObjectMapper();
//
//        try {
//            return om.readValue(response2.getBody(), OAuth2DTO.KakaoProfile.class);
//        } catch(Exception e) {
//            throw new MemberException(MemberErrorCode.OAUTH_USER_INFO_FAIL);
//        }
    }
}
