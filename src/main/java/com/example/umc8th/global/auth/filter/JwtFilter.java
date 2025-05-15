package com.example.umc8th.global.auth.filter;

import com.example.umc8th.global.apiPayload.CustomResponse;
import com.example.umc8th.global.apiPayload.code.BaseErrorCode;
import com.example.umc8th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc8th.global.apiPayload.exception.GeneralException;
import com.example.umc8th.global.auth.exception.AuthErrorCode;
import com.example.umc8th.global.auth.exception.AuthException;
import com.example.umc8th.global.auth.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String header = request.getHeader("Authorization");
            if (header != null && header.startsWith("Bearer ")) {
                String token = header.split(" ")[1];
                if (!jwtUtil.isValid(token)) {
                    throw new AuthException(AuthErrorCode.INVALID_TOKEN);
                }
                String username = jwtUtil.getUsername(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,
                        userDetails.getPassword(), userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            handleException(response, e);
        }
    }

    private void handleException(HttpServletResponse response, Exception e) throws IOException {
        BaseErrorCode code = e instanceof GeneralException generalException ? generalException.getCode() : GeneralErrorCode.UNAUTHORIZED_401;
        response.setStatus(code.getHttpStatus().value());
        response.setContentType("application/json; charset=UTF-8");

        CustomResponse<Object> customResponse = CustomResponse.onFailure(code.getCode(),
                code.getMessage(), e.getMessage());

        ObjectMapper om = new ObjectMapper();
        om.writeValue(response.getOutputStream(), customResponse);
    }
}