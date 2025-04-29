package com.example.umc8th.domain.member.converter;

import com.example.umc8th.domain.member.dto.request.MemberRequestDTO;
import com.example.umc8th.domain.member.dto.response.MemberResponseDTO;
import com.example.umc8th.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberConverter {

    // Member -> MemberResponseDTO.SignUp
    public static MemberResponseDTO.SignUp toSignUpResponseDTO(Member member) {
        return MemberResponseDTO.SignUp.builder()
                .id(member.getId())
                .username(member.getUsername())
                .build();
    }

    // SignUpRequestDTO -> Member
    public static Member toMember(MemberRequestDTO.SignUp reqDTO, PasswordEncoder passwordEncoder) {
        return Member.builder()
                .username(reqDTO.username())
                .password(passwordEncoder.encode(reqDTO.password()))
                .build();
    }

}
