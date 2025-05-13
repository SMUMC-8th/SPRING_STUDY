package com.example.umc8th.domain.member.service.command;

import com.example.umc8th.domain.member.dto.response.MemberResponseDTO;
import com.example.umc8th.domain.member.dto.response.OAuth2DTO;
import com.example.umc8th.domain.member.entity.Member;
import com.example.umc8th.domain.member.enums.Role;
import com.example.umc8th.domain.member.exception.MemberErrorCode;
import com.example.umc8th.domain.member.exception.MemberException;
import com.example.umc8th.domain.member.repository.MemberRepository;
import com.example.umc8th.global.config.JwtUtil;
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
    private final JwtUtil jwtProvider;

    @Override
    public MemberResponseDTO.MemberTokenDTO login(String code) {
        // 인가코드 토큰 가져오기
        RestTemplate restTemplate = new RestTemplate(); // 요청을 보내기 위한 RestTemplate
        HttpHeaders httpHeaders = new HttpHeaders(); // 헤더 선언

        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded"); // 헤더 설정

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>(); // RequestBody 설정
        map.add("grant_type", "authorization_code");
        map.add("client_id", clientId);
        map.add("redirect_uri", redirectURI);
        map.add("code", code);
        HttpEntity<MultiValueMap> request = new HttpEntity<>(map, httpHeaders); // Header와 Body를 이용하여 요청에 보낼 HttpEntity 생성

        // 요청을 보내서 응답 받아오기
        ResponseEntity<String> response1 = restTemplate.exchange(
                tokenURI, // URI
                HttpMethod.POST, // Method
                request, // Request 내용
                String.class); // 받을 응답 자료형

        ObjectMapper objectMapper = new ObjectMapper(); // String을 OAuth2DTO.OAuth2TokenDTO로 변경하기 위해 ObjectMapper 선언
        OAuth2DTO.OAuth2TokenDTO oAuth2TokenDTO = null;

        try {
            oAuth2TokenDTO = objectMapper.readValue(response1.getBody(), OAuth2DTO.OAuth2TokenDTO.class);
        } catch (Exception e) {
            throw new MemberException(MemberErrorCode.OAUTH_TOKEN_FAIL); // 토큰 DTO로 변경하지 못한 경우 Exception 보냄
        }

        // 토큰으로 정보 가져오기
        // 위와 흐름이 동일하여 생략하겠습니다. 아래는 RequestBody가 없어서 추가하지 않은 것을 볼 수 있습니다.
        restTemplate = new RestTemplate();
        httpHeaders = new HttpHeaders();

        httpHeaders.add("Authorization", "Bearer " + oAuth2TokenDTO.getAccess_token());
        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap> request1 = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response2 = restTemplate.exchange(
                userInfoURI,
                HttpMethod.GET,
                request1,
                String.class
        );

        OAuth2DTO.KakaoProfile profile = null;
        ObjectMapper om = new ObjectMapper();

        try {
            profile = om.readValue(response2.getBody(), OAuth2DTO.KakaoProfile.class);
        } catch(Exception e) {
            throw new MemberException(MemberErrorCode.OAUTH_USER_INFO_FAIL); // 사용자 정보를 가져오지 못한 경우 Exception 발생
        }

        // 회원가입이 되었으면 사용자 로그인 안되어있으면 회원가입 후 로그인
        String email = profile.getKakao_account().getEmail(); // Kakao에서의 Id를 가지고 Email로 변경

        // email을 찾고 있으면 member에 넣고 없으면 새로 만들어서 저장하고 넣는다.
        Member member = memberRepository.findByEmail(email).orElse(
                memberRepository.save(Member.builder()
                        .email(email)
                        .role(Role.USER)
                        .build())
        );

        // TokenDTO로 변경해서 저번 주차에 구현한 JWT 형태로 반환
        return MemberResponseDTO.MemberTokenDTO.builder()
                .accessToken(jwtProvider.createAccessToken(member))
                .refreshToken(jwtProvider.createRefreshToken(member))
                .build();
    }
}
