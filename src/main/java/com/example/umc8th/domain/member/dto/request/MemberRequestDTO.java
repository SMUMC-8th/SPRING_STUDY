package com.example.umc8th.domain.member.dto.request;

public class MemberRequestDTO {

    public record SignUp(
            String email,
            String username,
            String password
    ) {
    }

    public record login(
            String email,
            String password
    ){
    }
}
