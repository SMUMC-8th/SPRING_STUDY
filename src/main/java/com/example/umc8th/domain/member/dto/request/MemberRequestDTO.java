package com.example.umc8th.domain.member.dto.request;

public class MemberRequestDTO {

    public record SignUp(
            String username,
            String password
    ) {
    }
}
