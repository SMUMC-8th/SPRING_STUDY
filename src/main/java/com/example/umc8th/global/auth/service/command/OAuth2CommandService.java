package com.example.umc8th.global.auth.service.command;

import com.example.umc8th.domain.member.dto.MemberResponseDTO;

public interface OAuth2CommandService {

    MemberResponseDTO.LoginResponseDTO login(String code);
}
