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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class OAuthKakaoCommandService implements OAuth2CommandService{

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
        Member member = memberRepository.findByUsername(email).orElse(null);
        if (member == null) {
            member = MemberConverter.toMember(
                    kakaoUser.kakao_account().email(),
                    SocialLogin.KAKAO,
                    UserRole.ROLE_USER
            );
            memberRepository.save(member);
        }

        return MemberConverter.toLoginResponseDTO(
                jwtUtil.createAccessToken(member),
                jwtUtil.createRefreshToken(member)
        );
    }

    // 카카오 토큰 발급
    private OAuthKakaoResDTO.KakaoToken getKakaoToken(String code) {

        try {
            WebClient client = WebClient.builder()
                    .baseUrl("https://kauth.kakao.com/oauth/token")
                    .defaultHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            return client.post()
                    .bodyValue(
                            "grant_type=authorization_code" +
                                    "&client_id=" + clientId +
                                    "&redirect_uri=" + redirectURI +
                                    "&code=" + code +
                                    "&client_secret=" + clientSecret
                    )
                    .retrieve()
                    .bodyToMono(OAuthKakaoResDTO.KakaoToken.class)
                    .timeout(Duration.ofSeconds(5))
                    .block();
        } catch (Exception e) {
            if (e.getCause() instanceof TimeoutException) {
                throw new AuthException(AuthErrorCode.TIME_OUT);
            }
            throw new AuthException(AuthErrorCode.OAUTH_TOKEN_FAIL);
        }
    }

    // 카카오 유저 정보 조회
    private OAuthKakaoResDTO.KakaoUser getKakaoUser(String accessToken) {

        try {
            String token = "Bearer " + accessToken;
            WebClient client = WebClient.builder()
                    .baseUrl("https://kapi.kakao.com/v2/user/me")
                    .defaultHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            return client.get()
                    .header("Authorization", token)
                    .retrieve()
                    .bodyToMono(OAuthKakaoResDTO.KakaoUser.class)
                    .timeout(Duration.ofSeconds(5))
                    .block();
        } catch (Exception e) {
            if (e.getCause() instanceof TimeoutException) {
                throw new AuthException(AuthErrorCode.TIME_OUT);
            }
            throw new AuthException(AuthErrorCode.OAUTH_USER_INFO_FAIL);
        }
    }

}
