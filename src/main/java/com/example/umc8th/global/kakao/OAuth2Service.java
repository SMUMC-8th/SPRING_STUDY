package com.example.umc8th.global.kakao;

import com.example.umc8th.code.member.dto.MemberResponseDTO;

public interface OAuth2Service {
    MemberResponseDTO.LoginResponseDTO login(String code);
}
