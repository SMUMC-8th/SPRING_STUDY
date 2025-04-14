package com.example.umc8th.domain.member.dto;

import com.example.umc8th.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

public class MemberResponseDTO {

    @Getter
    @Builder
    public static class SignUpResponseDTO {
        private Long id;
        public static SignUpResponseDTO from(Member member) {
            return SignUpResponseDTO.builder()
                    .id(member.getId())
                    .build();
        }
    }
}
