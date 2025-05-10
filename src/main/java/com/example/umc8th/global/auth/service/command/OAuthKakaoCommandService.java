package com.example.umc8th.global.auth.service.command;

import com.example.umc8th.domain.member.converter.MemberConverter;
import com.example.umc8th.domain.member.dto.MemberResponseDTO;
import com.example.umc8th.domain.member.entity.Member;
import com.example.umc8th.domain.member.enums.SocialLogin;
import com.example.umc8th.domain.member.enums.UserRole;
import com.example.umc8th.domain.member.repository.MemberRepository;
import com.example.umc8th.global.auth.client.KakaoAuthClient;
import com.example.umc8th.global.auth.client.KakaoUserClient;
import com.example.umc8th.global.auth.dto.response.OAuthKakaoResDTO;
import com.example.umc8th.global.auth.exception.AuthException;
import com.example.umc8th.global.auth.exception.code.AuthErrorCode;
import com.example.umc8th.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    private final KakaoAuthClient kakaoAuthClient;
    private final KakaoUserClient kakaoUserClient;

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

        try {
            return kakaoAuthClient.getKakaoToken(
                    "authorization_code",
                    clientId,
                    redirectURI,
                    code,
                    clientSecret
            );
        } catch (Exception e) {
            throw new AuthException(AuthErrorCode.OAUTH_TOKEN_FAIL);
        }
    }

    // 카카오 유저 정보 조회
    private OAuthKakaoResDTO.KakaoUser getKakaoUser(String accessToken) {

        try {
            String token = "Bearer " + accessToken;
            return kakaoUserClient.getKakaoUser(token);
        } catch (Exception e) {
            throw new AuthException(AuthErrorCode.OAUTH_USER_INFO_FAIL);
        }
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
