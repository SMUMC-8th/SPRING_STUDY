package com.example.umc8th.global.auth.service.command;

import com.example.umc8th.domain.member.converter.MemberConverter;
import com.example.umc8th.domain.member.dto.MemberResponseDTO;
import com.example.umc8th.domain.member.entity.Member;
import com.example.umc8th.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtTokenCommandService implements TokenCommandService {

    private final JwtUtil jwtUtil;

    @Override
    public MemberResponseDTO.LoginResponseDTO createLoginToken(Member member) {
        String accessToken = jwtUtil.createAccessToken(member);
        String refreshToken = jwtUtil.createRefreshToken(member);
        return MemberConverter.toLoginResponseDTO(member.getId(), accessToken, refreshToken);
    }
}
