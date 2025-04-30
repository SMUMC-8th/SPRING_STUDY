package com.example.umc8th.domain.member.dto;

public class MemberRequestDTO {

    public record SignUpRequestDTO(String username, String password) {}
}
