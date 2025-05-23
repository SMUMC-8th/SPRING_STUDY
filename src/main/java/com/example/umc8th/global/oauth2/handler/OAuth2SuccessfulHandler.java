package com.example.umc8th.global.oauth2.handler;

import com.example.umc8th.domain.member.entity.Member;
import com.example.umc8th.domain.member.enums.SocialType;
import com.example.umc8th.domain.member.exception.MemberErrorCode;
import com.example.umc8th.domain.member.exception.MemberException;
import com.example.umc8th.domain.member.repository.MemberRepository;
import com.example.umc8th.global.auth.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedHashMap;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessfulHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;

    private static final String CLIENT_URL = "http://localhost:3000";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String socialType = ((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId();
        String email = "";
        if (socialType.equalsIgnoreCase(SocialType.KAKAO.name())) {
            email = getKakaoEmail(oAuth2User);
        }

        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new MemberException(MemberErrorCode.NOT_FOUND));

        sendResponse(member, response);
        response.sendRedirect(CLIENT_URL);
    }

    private String getKakaoEmail(OAuth2User oAuth2User) {
        return (String) ((LinkedHashMap<String, Object>)oAuth2User.getAttribute("kakao_account")).get("email");
    }

    private void sendResponse(Member member, HttpServletResponse response) {
        addCookie(response, "access_token", jwtUtil.createAccessToken(member), 5);
        addCookie(response, "refresh_token", jwtUtil.createRefreshToken(member), 5);
    }

    private void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
}
