package com.example.umc8th.domain.member.converter;

import com.example.umc8th.domain.member.dto.MemberResponseDTO;
import com.example.umc8th.domain.member.entity.Member;

public class MemberConverter {

    // username, password -> Member(SignUp Entity)
    public static Member toMember(String username, String password) {
        return Member.builder()
                .username(username)
                .password(password)
                .build();
    }

    // Member -> SignUpResponseDTO
    public static MemberResponseDTO.SignUpResponseDTO toSignUpResponseDTO(Member member) {
        return MemberResponseDTO.SignUpResponseDTO.builder()
                .id(member.getId())
                .build();
    }

    // id, accessToken, refreshToken -> LoginResponseDTO
    public static MemberResponseDTO.LoginResponseDTO toLoginResponseDTO(
            Long id,
            String accessToken,
            String refreshToken
    ) {
        return MemberResponseDTO.LoginResponseDTO.builder()
                .id(id)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
