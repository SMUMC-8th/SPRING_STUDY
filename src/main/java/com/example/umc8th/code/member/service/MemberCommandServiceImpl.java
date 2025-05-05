package com.example.umc8th.code.member.service;

import com.example.umc8th.code.member.dto.MemberRequestDTO;
import com.example.umc8th.code.member.dto.MemberResponseDTO;
import com.example.umc8th.code.member.entity.Member;
import com.example.umc8th.code.member.exception.MemberErrorCode;
import com.example.umc8th.code.member.exception.MemberException;
import com.example.umc8th.code.member.repo.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenCommandService tokenCommandService;

    @Override
    public Member signUp(MemberRequestDTO.SignUpRequestDTO dto) {
        return memberRepository.save(
                Member.builder()
                        .username(dto.getUsername())
                        .password(passwordEncoder.encode(dto.getPassword()))
                        .build()
        );
    }

    @Override
    public MemberResponseDTO.LoginResponseDTO login(MemberRequestDTO.LoginRequestDTO dto) {
        Member member = memberRepository.findByUsername(dto.getUsername()).orElseThrow(() ->
                new MemberException(MemberErrorCode.NOT_FOUND));
        if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.BAD_CREDENTIAL);
        }
        return tokenCommandService.createLoginToken(member); // 유저 정보로 토큰만들기, 참고로 DTO에는 id, accessToken, refreshToken이 존재합니다.
    }

}
