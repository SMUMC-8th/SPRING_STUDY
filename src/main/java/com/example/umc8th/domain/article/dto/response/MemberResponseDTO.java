package com.example.umc8th.domain.article.dto.response;

import com.example.umc8th.domain.member.entity.Member;
import lombok.Builder;

public class MemberResponseDTO {

    @Builder
    public record SignUpResponseDTO(
            Long id
    ) {
        public static SignUpResponseDTO from(Member member) {
            return SignUpResponseDTO.builder()
                    .id(member.getId())
                    .build();
        }
    }
}
