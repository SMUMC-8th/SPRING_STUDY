package com.example.umc8th.domain.member.dto.response;

import lombok.Builder;

public class MemberResponseDTO {

    @Builder
    public record SignUp(
            Long id,
            String username
    ) {
    }
}
