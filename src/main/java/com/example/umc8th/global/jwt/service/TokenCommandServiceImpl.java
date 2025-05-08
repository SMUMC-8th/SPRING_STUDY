package com.example.umc8th.global.jwt.service;

import com.example.umc8th.domain.member.entity.Member;
import com.example.umc8th.global.jwt.dto.JwtDTO;
import com.example.umc8th.global.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TokenCommandServiceImpl implements TokenCommandService{

    private final JwtUtil jwtUtil;

    @Override
    public JwtDTO createJwtToken(Member member) {
        String accessToken = jwtUtil.createAccessToken(member);
        String refreshToken = jwtUtil.createRefreshToken(member);
        return JwtDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
