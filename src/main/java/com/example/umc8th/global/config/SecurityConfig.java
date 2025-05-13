package com.example.umc8th.global.config;

import com.example.umc8th.global.auth.CustomUserDetailsService;

import com.example.umc8th.global.auth.filter.JwtFilter;
import com.example.umc8th.global.auth.handler.CustomAccessDeniedHandler;
import com.example.umc8th.global.auth.handler.CustomEntryPoint;
import com.example.umc8th.global.auth.util.JwtUtil;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomEntryPoint customEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private String[] allowUrl = {
            "/auth/sign-up",
            "/auth/login", // 로그인 URL 추가
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/oauth2/callback/kakao"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers(allowUrl).permitAll()
                        .anyRequest().authenticated()
                )
                .cors(cors -> cors.configurationSource(CorsConfig.apiConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .oauth2Login(Customizer.withDefaults())
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(customEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                )
        ;

        return http.build();
    }

    @Bean
    Filter jwtFilter() {
        return new JwtFilter(jwtUtil, customUserDetailsService);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }
}