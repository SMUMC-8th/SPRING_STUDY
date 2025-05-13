package com.example.umc8th.service.command.impl;

import com.example.umc8th.dto.MemberResponseDTO;
import com.example.umc8th.entity.Member;
import com.example.umc8th.global.config.JwtUtil;
import com.example.umc8th.repository.MemberRepository;
import com.example.umc8th.service.command.TokenCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenCommandServiceImpl implements TokenCommandService {
    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;

    @Override
    public MemberResponseDTO.LoginResponseDTO createLoginToken(Member member) {
        // 토큰 생성
        String accessToken = jwtUtil.createAccessToken(member);
        String refreshToken = jwtUtil.createRefreshToken(member);

        // Member 엔티티에 토큰 저장
        member.updateTokens(accessToken, refreshToken);
        memberRepository.save(member); // 저장 반영

        // DTO 반환
        return MemberResponseDTO.LoginResponseDTO.builder()
                .id(member.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public List<String> createTokens(Member member){
        List<String> result = new ArrayList<>();
        result.add(jwtUtil.createAccessToken(member));
        result.add(jwtUtil.createRefreshToken(member));
        return result;
    }
}
