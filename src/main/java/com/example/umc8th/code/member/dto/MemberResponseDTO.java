package com.example.umc8th.code.member.dto;

import com.example.umc8th.code.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class LoginResponseDTO {

        private Long id;
        private String email;
        private String accessToken;
        private String refreshToken;

    }
}
