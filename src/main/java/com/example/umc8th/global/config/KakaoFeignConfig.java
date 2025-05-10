package com.example.umc8th.global.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class KakaoFeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate ->
                requestTemplate.header(
                        "Content-Type",
                        "application/x-www-form-urlencoded;charset=utf-8"
                );
    }
}
