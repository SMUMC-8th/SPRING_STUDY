package com.example.umc8th.global.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Collections;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CorsConfig {

    public static CorsConfigurationSource apiConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // 허용할 Origin(출처) 리스트
        ArrayList<String> allowedOriginPatterns = new ArrayList<>();
        allowedOriginPatterns.add("http://localhost:8080");
        allowedOriginPatterns.add("http://127.0.0.1:8080");
        allowedOriginPatterns.add("http://localhost:3000");
        allowedOriginPatterns.add("http://127.0.0.1:3000");
        allowedOriginPatterns.add("http://localhost:5173");
        allowedOriginPatterns.add("http://127.0.0.1:5173");
        allowedOriginPatterns.add("http://localhost:5000");
        allowedOriginPatterns.add("http://127.0.0.1:5000");

        configuration.setAllowedOrigins(allowedOriginPatterns); // 허용할 Origin 설정

        // 허용할 HTTP 메서드
        ArrayList<String> allowedHttpMethods = new ArrayList<>();
        allowedHttpMethods.add("GET");
        allowedHttpMethods.add("POST");
        allowedHttpMethods.add("PUT");
        allowedHttpMethods.add("DELETE");
        allowedHttpMethods.add("PATCH");
        allowedHttpMethods.add("OPTIONS"); // Preflight 요청을 위해 OPTIONS 추가
        configuration.setAllowedMethods(allowedHttpMethods);

        // 모든 요청 헤더 허용
        configuration.setAllowedHeaders(Collections.singletonList("*"));

        // 인증 정보(쿠키, 헤더)를 포함한 요청 허용
        configuration.setAllowCredentials(true);

        // CORS 설정 적용 경로
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
