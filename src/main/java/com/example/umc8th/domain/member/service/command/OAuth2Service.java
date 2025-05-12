package com.example.umc8th.domain.member.service.command;

import com.example.umc8th.global.jwt.dto.JwtDTO;

public interface OAuth2Service {

    JwtDTO loginWithKakao(String code);
}
