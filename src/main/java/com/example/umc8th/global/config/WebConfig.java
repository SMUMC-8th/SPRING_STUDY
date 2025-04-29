package com.example.umc8th.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Spring MVC 에서 CORS 정책을 설정하는 메서드
    // 어떤 경로에 대해서 어떤 출처를 허용할지, 어떤 메서드를 허용할지를 지정
    // 인자로 넘어온 CorsRegistry 를 통해 설정
    @Override
    public void addCorsMappings(CorsRegistry registry) {
                // 모든 요청 경로에 대해 CORS 설정을 적용
        registry.addMapping("/**")
                // 실제로 배포할 때에는 .allowedOrigins("https://example.com", "https://admin.example.com") 이런식으로
                // 특정 url 만 적어야 함, 여기서는 테스트 용도로 일단 요렇게 함.
                .allowedOriginPatterns("*") // 모든 출처 허용
                .allowedMethods("GET", "POST")
                // 쿠키, 세션 ID 같은 인증 정보를 요청에 같이 보내는 걸 허용
                .allowCredentials(true); // 세션 쿠키 같이 보내기 허용
    }
}