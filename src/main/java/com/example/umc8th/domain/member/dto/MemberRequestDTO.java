package com.example.umc8th.domain.member.dto;

import lombok.Getter;

public class MemberRequestDTO {

    @Getter
    public static class SignUpRequestDTO {
        private String username;
        private String password;
    }

    @Getter
    public static class LoginRequestDTO {
        private String username;
        private String password;
    }
}
