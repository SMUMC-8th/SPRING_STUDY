package com.example.umc8th.global.config;

import com.example.umc8th.global.auth.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            // 헤더에서 토큰 추출
            String authHeader = request.getHeader("Authorization");
            String token = null;

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7); // "Bearer " 제외하고 토큰만 추출
            }

            // 토큰 검증 및 SecurityContextHolder 설정
            if (token != null && jwtUtil.isValid(token)) {
                String username = jwtUtil.getUsername(token); // Subject에서 username 추출

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // 사용자 정보 가져오기
                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

                    // 인증 객체 생성
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    // 인증 객체를 SecurityContext에 등록
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

            // 다음 필터 진행
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            // 에러 처리 (로깅 등)
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 응답
        }
    }
}
