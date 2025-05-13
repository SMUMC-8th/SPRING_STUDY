package com.example.umc8th.domain.member.service;

import com.example.umc8th.domain.member.dto.MemberResponseDTO;

public interface OAuth2Service {
    MemberResponseDTO.MemberTokenDTO login(String code);
}