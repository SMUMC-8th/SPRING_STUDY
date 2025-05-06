package com.example.umc8th.domain.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MemberResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class LoginResponseDTO {
        private Long id;
        private String accessToken;
        private String refreshToken;
    }
}
