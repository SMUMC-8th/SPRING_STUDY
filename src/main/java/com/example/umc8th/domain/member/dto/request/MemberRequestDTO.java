package com.example.umc8th.domain.member.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MemberRequestDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SignUpRequestDTO {
        private String username;
        private String password;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class LoginRequestDTO {
        private String username;
        private String password;
    }
}
