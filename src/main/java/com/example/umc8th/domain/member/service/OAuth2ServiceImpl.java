package com.example.umc8th.domain.member.service;

import com.example.umc8th.domain.member.dto.MemberResponseDTO;
import com.example.umc8th.domain.member.dto.OAuth2DTO;
import com.example.umc8th.domain.member.entity.Member;
import com.example.umc8th.domain.member.exception.MemberErrorCode;
import com.example.umc8th.domain.member.exception.MemberException;
import com.example.umc8th.domain.member.repository.MemberRepository;
import com.example.umc8th.global.auth.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class OAuth2ServiceImpl implements OAuth2Service {

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String tokenURI;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String userInfoURI;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectURI;

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public MemberResponseDTO.MemberTokenDTO login(String code) {
        // 1. 인가 코드 → 액세스 토큰
        OAuth2DTO.OAuth2TokenDTO tokenDTO = requestAccessToken(code);

        // 2. 액세스 토큰 → 사용자 정보
        OAuth2DTO.KakaoProfile profile = requestUserInfo(tokenDTO.getAccess_token());

        // 3. 이메일로 회원 조회 또는 신규 회원 등록
        String email = profile.getKakao_account().getEmail().toString();
        Member member = findOrCreateMember(email);

        // 4. JWT 생성 및 반환
        return createTokenDTO(member);
    }

    // 🔹 1. 인가 코드 → 액세스 토큰
    private OAuth2DTO.OAuth2TokenDTO requestAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectURI);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                tokenURI,
                HttpMethod.POST,
                request,
                String.class
        );

        try {
            return objectMapper.readValue(response.getBody(), OAuth2DTO.OAuth2TokenDTO.class);
        } catch (Exception e) {
            throw new MemberException(MemberErrorCode.OAUTH_TOKEN_FAIL);
        }
    }

    // 🔹 2. 액세스 토큰 → 사용자 정보
    private OAuth2DTO.KakaoProfile requestUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                userInfoURI,
                HttpMethod.GET,
                request,
                String.class
        );

        try {
            return objectMapper.readValue(response.getBody(), OAuth2DTO.KakaoProfile.class);
        } catch (Exception e) {
            throw new MemberException(MemberErrorCode.OAUTH_USER_INFO_FAIL);
        }
    }

    // 🔹 3. 사용자 조회 또는 회원가입
    private Member findOrCreateMember(String email) {
        return memberRepository.findByEmail(email)
                .orElseGet(() -> memberRepository.save(
                        Member.builder()
                                .email(email)
                                //.role("ROLE_USER")
                                .build()
                ));
    }

    // 🔹 4. JWT 생성 후 DTO로 반환
    private MemberResponseDTO.MemberTokenDTO createTokenDTO(Member member) {
        return MemberResponseDTO.MemberTokenDTO.builder()
                .accessToken(jwtUtil.createAccessToken(member))
                .refreshToken(jwtUtil.createRefreshToken(member))
                .build();
    }
}
