package com.example.umc8th.dto;


import com.example.umc8th.entity.Member;
import com.example.umc8th.global.config.JwtUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberResponseDTO {
    private JwtUtil jwtUtil;

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
    public static class LoginResponseDTO{
        private Long id;
        private String accessToken;
        private String refreshToken;
        public static LoginResponseDTO from(Member member) {
            return LoginResponseDTO.builder()
                    .id(member.getId())
                    .accessToken(member.getAccessToken())
                    .refreshToken(member.getRefreshToken())
                    .build();
        }

    }

    @Getter
    @Builder
    public static class MemberTokenDTO{
        private Long id;
        private String accessToken;
        private String refreshToken;
        public static MemberTokenDTO from(Member member) {
            return MemberTokenDTO.builder()
                    .id(member.getId())
                    .accessToken(member.getAccessToken())
                    .refreshToken(member.getRefreshToken())
                    .build();
        }
    }
}
