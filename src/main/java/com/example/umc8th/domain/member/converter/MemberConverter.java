package com.example.umc8th.domain.member.converter;

import com.example.umc8th.domain.member.dto.MemberResponseDTO;
import com.example.umc8th.domain.member.entity.Member;
import com.example.umc8th.domain.member.enums.SocialLogin;
import com.example.umc8th.domain.member.enums.UserRole;

public class MemberConverter {

    // username, password -> Member(SignUp Entity)
    public static Member toMember(String username, String password) {
        return Member.builder()
                .username(username)
                .password(password)
                .role(UserRole.ROLE_USER)
                .socialLogin(null)
                .build();
    }

    // 소셜 로그인 전용 (회원가입)
    public static Member toMember(
            String username,
            SocialLogin socialLogin,
            UserRole userRole
    ) {
        return Member.builder()
                .username(username)
                .password(null)
                .socialLogin(socialLogin)
                .role(userRole)
                .build();
    }

    // Member -> SignUpResponseDTO
    public static MemberResponseDTO.SignUpResponseDTO toSignUpResponseDTO(Member member) {
        return MemberResponseDTO.SignUpResponseDTO.builder()
                .id(member.getId())
                .build();
    }

    // accessToken, refreshToken -> LoginResponseDTO
    public static MemberResponseDTO.LoginResponseDTO toLoginResponseDTO(
            String accessToken,
            String refreshToken
    ) {
        return MemberResponseDTO.LoginResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
