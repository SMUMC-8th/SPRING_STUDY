package com.example.umc8th.domain.member.service.command;


import com.example.umc8th.domain.member.dto.MemberResponseDTO;

public interface OAuth2Service {
    MemberResponseDTO.LoginResponseDTO login(String provider, String code);
}
