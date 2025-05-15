package com.example.umc8th.domain.member.service.command;

import com.example.umc8th.domain.member.dto.MemberResponseDTO;
import com.example.umc8th.domain.member.entity.Member;
import com.example.umc8th.global.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenCommandServiceImpl implements TokenCommandService {

    private final JwtUtil jwtUtil;

    public MemberResponseDTO.LoginResponseDTO createLoginToken(Member member) {
        String accessToken = jwtUtil.createAccessToken(member);
        String refreshToken = jwtUtil.createRefreshToken(member);
        return MemberResponseDTO.LoginResponseDTO.builder()
                .id(member.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
