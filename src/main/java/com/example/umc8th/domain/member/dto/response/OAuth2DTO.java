package com.example.umc8th.domain.member.dto.response;

public class OAuth2DTO {

    public record OAuth2TokenDTO(
            String token_type,
            String access_token,
            String refresh_token,
            Long expires_in,
            Long refresh_token_expires_in,
            String scope
    ) {}

    public record KakaoProfile(
            Long id,
            String connected_at,
            Properties properties,
            KakaoAccount kakao_account
    ) {
        public record Properties(
                String nickname,
                String profile_image,
                String thumbnail_image
        ) {}

        public record KakaoAccount(
                String email,
                Boolean is_email_verified,
                Boolean email_needs_agreement,
                Boolean has_email,
                Boolean profile_nickname_needs_agreement,
                Boolean profile_image_needs_agreement,
                Boolean email_needs_argument,
                Boolean is_email_valid,
                Profile profile
        ) {
            public record Profile(
                    String nickname,
                    String thumbnail_image_url,
                    String profile_image_url,
                    Boolean is_default_nickname,
                    Boolean is_default_image
            ) {}
        }
    }
}
