package com.example.umc8th.global.jwt.dto;

import lombok.Builder;

@Builder
public record JwtDTO(
        String accessToken,
        String refreshToken
) {
}
