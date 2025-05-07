package com.example.umc8th.domain.member.dto;

import lombok.Builder;

public class MemberResponseDTO {

    @Builder
    public record SignUpResponseDTO(Long id) {}

    @Builder
    public record LoginResponseDTO(Long id, String accessToken, String refreshToken) {}
}
