package com.example.umc8th.global.auth.filter;

import com.example.umc8th.global.apiPayload.GlobalResponse;
import com.example.umc8th.global.auth.service.query.CustomUserDetailsService;
import com.example.umc8th.global.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            // 헤더에서 토큰 추출
            String token = request.getHeader("Authorization");
            token = token != null ? token.replace("Bearer ", "") : null;
            // 토큰 검증
            if (token != null && jwtUtil.isValid(token)) {
                // 토큰에서 사용자 정보 가져오기
                String username = jwtUtil.getUsername(token);
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                // SecurityContextHolder에 인증 정보 넣기
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            // 에러 처리
            GlobalResponse<Object> error = GlobalResponse.onFailure(
                    "Authentication500",
                    e.getMessage()
            );

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), error);
        }
    }
}