package com.example.umc8th.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.security.Security;
import java.util.List;

@Configuration
public class SecurityConfig {

    // private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    private String[] allowUrl = {
            "/auth/sign-up",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
    };

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers(allowUrl).permitAll()
                        .anyRequest().authenticated()
                )
                .cors(Customizer.withDefaults()) // cors 활성화 - security
                // CSRF 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                // http basic 인증 방식 비활성화
                .httpBasic(AbstractHttpConfigurer::disable)
                // formLogin 설정
                .formLogin(formLogin -> formLogin
                                .securityContextRepository(securityContextRepository())
                                .defaultSuccessUrl("/swagger-ui/index.html")
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )
                .securityContext(context -> context
                        .securityContextRepository(securityContextRepository())
                );

        return http.build();

    }

    @Bean
        // AuthenticationProvider에서 사용할 passwordEncoder 설정
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
        // SecurityContextRepository 빈 등록
    SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

    // WebConfig 는 MVC 용 - 현재 나는 security를 사용하기에 security cors 설정도 해야 한다.
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
