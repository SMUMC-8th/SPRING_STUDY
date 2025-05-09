package com.example.umc8th.global.auth.service.command;

import com.example.umc8th.domain.member.converter.MemberConverter;
import com.example.umc8th.domain.member.dto.MemberResponseDTO;
import com.example.umc8th.domain.member.entity.Member;
import com.example.umc8th.domain.member.enums.SocialLogin;
import com.example.umc8th.domain.member.enums.UserRole;
import com.example.umc8th.domain.member.repository.MemberRepository;
import com.example.umc8th.global.auth.dto.response.OAuthKakaoResDTO;
import com.example.umc8th.global.auth.exception.AuthException;
import com.example.umc8th.global.auth.exception.code.AuthErrorCode;
import com.example.umc8th.global.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OAuthKakaoCommandService implements OAuth2CommandService{

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String tokenURI; // Resource Server에 토큰 요청시 사용할 URI

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String userInfoURI; // 사용자 정보 가져올 때 사용할 URI

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId; // API KEY

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectURI; // 설정한 Redirect uri

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String clientSecret;

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @Override
    public MemberResponseDTO.LoginResponseDTO login(String code) {

        OAuthKakaoResDTO.KakaoToken kakaoToken = getKakaoToken(code);
        OAuthKakaoResDTO.KakaoUser kakaoUser = getKakaoUser(kakaoToken.access_token());
        String email = kakaoUser.kakao_account().email();
        Member member = findMember(email);
        if (member == null) {
            member = createMember(kakaoUser);
        }
        return MemberConverter.toLoginResponseDTO(
                jwtUtil.createAccessToken(member),
                jwtUtil.createRefreshToken(member)
        );
    }

    // 카카오 토큰 발급
    private OAuthKakaoResDTO.KakaoToken getKakaoToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("client_id", clientId);
        map.add("redirect_uri", redirectURI);
        map.add("code", code);
        map.add("client_secret", clientSecret);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                tokenURI,
                HttpMethod.POST,
                request,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        OAuthKakaoResDTO.KakaoToken kakaoToken;
        try {
            kakaoToken = objectMapper.readValue(response.getBody(), OAuthKakaoResDTO.KakaoToken.class);
        } catch (Exception e) {
            throw new AuthException(AuthErrorCode.OAUTH_TOKEN_FAIL);
        }
        return kakaoToken;
    }

    // 카카오 유저 정보 조회
    private OAuthKakaoResDTO.KakaoUser getKakaoUser(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Authorization", "Bearer " + accessToken);
        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<String> request = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(
                userInfoURI,
                HttpMethod.GET,
                request,
                String.class
        );

        OAuthKakaoResDTO.KakaoUser kakaoUser;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            kakaoUser = objectMapper.readValue(response.getBody(), OAuthKakaoResDTO.KakaoUser.class);
        } catch (Exception e) {
            throw new AuthException(AuthErrorCode.OAUTH_USER_INFO_FAIL);
        }
        return kakaoUser;
    }

    // 회원가입 여부 확인
    private Member findMember(String username) {
        return memberRepository.findByUsername(username).orElse(null);
    }

    // 소셜 회원가입
    private Member createMember(OAuthKakaoResDTO.KakaoUser kakaoUser) {
        Member member = MemberConverter.toMember(
                kakaoUser.kakao_account().email(),
                SocialLogin.KAKAO,
                UserRole.ROLE_USER
        );
        return memberRepository.save(member);
    }
}
