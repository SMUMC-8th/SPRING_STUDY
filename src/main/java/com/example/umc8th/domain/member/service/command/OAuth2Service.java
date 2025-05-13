package com.example.umc8th.domain.member.service.command;

import com.example.umc8th.domain.member.dto.response.MemberResponseDTO;

public interface OAuth2Service {
    MemberResponseDTO.MemberTokenDTO login(String code);
}
