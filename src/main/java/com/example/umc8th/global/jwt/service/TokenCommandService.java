package com.example.umc8th.global.jwt.service;

import com.example.umc8th.domain.member.entity.Member;
import com.example.umc8th.global.jwt.dto.JwtDTO;

public interface TokenCommandService {
    JwtDTO createJwtToken(Member member);
}
