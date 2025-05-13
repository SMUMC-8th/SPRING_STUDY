package com.example.umc8th.service.oauth;

import com.example.umc8th.dto.MemberResponseDTO;
import com.example.umc8th.dto.OAuth2DTO;
import com.example.umc8th.entity.Member;
import com.example.umc8th.exception.MemberErrorCode;
import com.example.umc8th.exception.MemberException;
import com.example.umc8th.global.config.JwtUtil;
import com.example.umc8th.repository.MemberRepository;
import com.example.umc8th.service.command.TokenCommandService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// OAuth2Service Impl
@Service
@RequiredArgsConstructor
@Slf4j
public class OAuth2ServiceImpl implements OAuth2Service{

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String tokenURI; // Resource Server에 토큰 요청시 사용할 URI

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String userInfoURI; // 사용자 정보 가져올 때 사용할 URI

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId; // API KEY

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectURI; // 설정한 Redirect uri

    private final MemberRepository memberRepository;
    private final TokenCommandService tokenCommandService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public MemberResponseDTO.MemberTokenDTO login(String code) {

        OAuth2DTO.OAuth2TokenDTO tokenDTO = getKakaoAccessToken(code);

        OAuth2DTO.KakaoProfile profile = getKakaoProfile(tokenDTO.getAccess_token());

        Member member = processMemberRegistration(profile);

        return generateTokenResponse(member);
    }

    private OAuth2DTO.OAuth2TokenDTO getKakaoAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("redirect_uri", redirectURI);
        body.add("code", code);

        ResponseEntity<String> response = new RestTemplate().exchange(
                tokenURI,
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                String.class
        );

        return parseResponse(response, OAuth2DTO.OAuth2TokenDTO.class, MemberErrorCode.OAUTH_TOKEN_FAIL);
    }

    private OAuth2DTO.KakaoProfile getKakaoProfile(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        ResponseEntity<String> response = restTemplate.exchange(
                userInfoURI,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
        );

        return parseResponse(response, OAuth2DTO.KakaoProfile.class, MemberErrorCode.OAUTH_USER_INFO_FAIL);
    }

    private <T> T parseResponse(ResponseEntity<String> response, Class<T> valueType, MemberErrorCode errorCode) {
        try {
            return objectMapper.readValue(response.getBody(), valueType);
        } catch (Exception e) {
            throw new MemberException(errorCode);
        }
    }

    private Member processMemberRegistration(OAuth2DTO.KakaoProfile profile) {
        String email = extractEmail(profile);

        return memberRepository.findByEmail(email)
                .orElseGet(() -> registerNewMember(email));
    }

    private String extractEmail(OAuth2DTO.KakaoProfile profile) {
        return Optional.ofNullable(profile.getKakao_account())
                .map(OAuth2DTO.KakaoProfile.KakaoAccount::getEmail) // email 그대로 사용
                .orElseThrow(() -> new MemberException(MemberErrorCode.OAUTH_TOKEN_FAIL));
    }

    private Member registerNewMember(String email) {
        Member tempMember = Member.builder()
                .email(email)
                .password(generateTemporaryPassword())
                .username(extractUsernameFromEmail(email))
                .role("ROLE_USER")
                .build();

        List<String> tokens = tokenCommandService.createTokens(tempMember);
        Member member = Member.builder()
                .email(tempMember.getEmail())
                .password(tempMember.getPassword())
                .username(tempMember.getUsername())
                .role(tempMember.getRole())
                .accessToken(tokens.get(0))
                .refreshToken(tokens.get(1))
                .build();

        return memberRepository.save(member);
    }

    private String generateTemporaryPassword() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 15);
    }

    private String extractUsernameFromEmail(String email) {
        return email.split("@")[0];
    }

    private MemberResponseDTO.MemberTokenDTO generateTokenResponse(Member member) {
        List<String> tokens = tokenCommandService.createTokens(member);

        return MemberResponseDTO.MemberTokenDTO.builder()
                .accessToken(member.getAccessToken())
                .refreshToken(member.getRefreshToken())
                .id(member.getId())
                .build();
    }
}