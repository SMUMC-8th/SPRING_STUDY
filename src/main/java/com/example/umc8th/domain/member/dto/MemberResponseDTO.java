package com.example.umc8th.domain.member.dto;

import com.example.umc8th.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

public class MemberResponseDTO {

    @Getter
    @Builder
    public static class SignUpResponseDTO {
        private Long id;
        public static SignUpResponseDTO from(Member member) {
            return SignUpResponseDTO.builder()
                    .id(member.getId())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class LoginResponseDTO {
        private Long id;
        private String accessToken;
        private String refreshToken;

        public static LoginResponseDTO from(Long id, String accessToken, String refreshToken) {
            return LoginResponseDTO.builder()
                    .id(id)
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        }
    }
}
