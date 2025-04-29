package com.example.umc8th.code.member;

import lombok.Getter;

public class MemberRequestDTO {

    @Getter
    public static class SignUpRequestDTO {
        private String username;
        private String password;
    }
}
