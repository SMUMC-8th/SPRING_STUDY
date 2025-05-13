package com.example.umc8th.domain.member.dto.response;

import com.example.umc8th.domain.member.entity.Member;
import lombok.Builder;

public class MemberResponseDTO {

    @Builder
    public record SignUpResponseDTO (
            Long id
    ) {
        public static SignUpResponseDTO from(Member member) {
            return SignUpResponseDTO.builder()
                    .id(member.getId())
                    .build();
        }
    }

    @Builder
    public record LoginResponseDTO (
            Long id,
            String accessToken,
            String refreshToken
    ) {
        public static LoginResponseDTO from(Member member) {
            return LoginResponseDTO.builder()
                    .id(member.getId())
                    .accessToken(member.getAccessToken())
                    .refreshToken(member.getRefreshToken())
                    .build();
        }
    }

    @Builder
    public record MemberTokenDTO (
            String accessToken,
            String refreshToken
    ) {}
}
