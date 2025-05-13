package com.example.umc8th.global.auth.dto.response;


import java.time.LocalDateTime;

public class OAuthKakaoResDTO {

    public record KakaoToken(
            String token_type,
            String access_token,
            Integer expires_in,
            String refresh_token,
            Integer refresh_token_expires_in,
            String scope
    ){}

    public record KakaoUser(
            String id,
            Boolean has_signed_up,
            LocalDateTime connected_at,
            Properties properties,
            KakaoAccount kakao_account,
            Profile profile
    ){}

    public record Properties(
            String nickname,
            String profile_image,
            String thumbnail_image
    ){}

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
    ){}

    public record Profile(
            String nickname,
            String thumbnail_image_url,
            String profile_image_url,
            Boolean is_default_image,
            Boolean is_default_nickname
    ){}
}
