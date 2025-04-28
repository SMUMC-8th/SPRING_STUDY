package com.example.umc8th.domain.member.dto.request;

import lombok.Getter;

public class MemberRequestDTO {

    public record SignUpRequestDTO(
            String username,
            String password
    ) {}
}
