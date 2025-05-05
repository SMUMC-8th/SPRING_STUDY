package com.example.umc8th.code.member.service;


import com.example.umc8th.code.member.entity.Member;
import com.example.umc8th.code.member.dto.MemberResponseDTO;
import com.example.umc8th.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenCommandService {

    private final JwtUtil jwtUtil;

    public MemberResponseDTO.LoginResponseDTO createLoginToken(Member member) {
        return MemberResponseDTO.LoginResponseDTO.builder()
                .id(member.getId())
                .accessToken(jwtUtil.createAccessToken(member))
                .refreshToken(jwtUtil.createRefreshToken(member))
                .build();
    }
}
