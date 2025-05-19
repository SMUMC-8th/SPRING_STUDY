package com.example.umc8th.global.kakao;

import com.example.umc8th.code.member.dto.MemberResponseDTO;
import com.example.umc8th.code.member.entity.Member;
import com.example.umc8th.code.member.exception.MemberException;
import com.example.umc8th.code.member.exception.MemberErrorCode;
import com.example.umc8th.code.member.repo.MemberRepository;
import com.example.umc8th.global.jwt.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static com.example.umc8th.code.member.Role.ROLE_USER;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2ServiceImpl implements OAuth2Service{

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String tokenURI;    // Resource Server에 토큰 요청시 사용할 URI

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String userInfoURI; // 사용자 정보 가져올 때 사용할 URI

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId; // API KEY

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectURI; // 설정한 Redirect uri

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper;

    @Override
    public MemberResponseDTO.LoginResponseDTO login(String code) {

        OAuth2DTO.OAuth2TokenDTO token = requestToken(code);
        OAuth2DTO.KakaoProfile profile = requestUserProfile(token.getAccess_token());
        Member member = createMember(profile);
        String accessToken = jwtUtil.createAccessToken(member);
        String refreshToken = jwtUtil.createRefreshToken(member);

        return MemberResponseDTO.LoginResponseDTO.builder()
                .id(member.getId())
                .email(member.getEmail())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private OAuth2DTO.OAuth2TokenDTO requestToken(String code) {
        //인가코드 토큰
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded");   // 헤더 설정

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();    // RequestBody 설정
        map.add("grant_type", "authorization_code");
        map.add("client_id", clientId);
        map.add("redirect_uri", redirectURI);
        map.add("code", code);

        HttpEntity<MultiValueMap> request = new HttpEntity<>(map, httpHeaders);


        try {
            // 요청을 보내서 응답 받아오기
            ResponseEntity<String> response1 = restTemplate.exchange(tokenURI,
                    HttpMethod.POST, // Method
                    request, String.class);

            log.info("[ 카카오 토큰 ]: {}", response1.getBody());
            String body = response1.getBody();
            log.info("[ 카카오 토큰2 ]: {}", body);

            return objectMapper.readValue(body, OAuth2DTO.OAuth2TokenDTO.class);

        } catch (RestClientException | JsonProcessingException e) {
                throw new MemberException(MemberErrorCode.OAUTH_TOKEN_FAIL);
        }
    }

    private OAuth2DTO.KakaoProfile requestUserProfile(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<?> request = new HttpEntity<>(headers);

        OAuth2DTO.KakaoProfile profile = null;
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    userInfoURI, HttpMethod.GET, request, String.class);
            profile = objectMapper.readValue(response.getBody(), OAuth2DTO.KakaoProfile.class);
            return profile;
        } catch (Exception e) {
            throw new MemberException(MemberErrorCode.OAUTH_USER_INFO_FAIL);
        }
    }

    private Member createMember(OAuth2DTO.KakaoProfile profile){
        String email = profile.getId().toString(); // Kakao에서의 Id를 가지고 Email로 변경

        // email을 찾고 있으면 member에 넣고 없으면 새로 만들어서 저장하고 넣는다.
        Member member = memberRepository.findByEmail(email).orElse(
                memberRepository.save(Member.builder()
                        .email(email)
                        .role(ROLE_USER)
                        .build())
        );
        return member;
    }
}
