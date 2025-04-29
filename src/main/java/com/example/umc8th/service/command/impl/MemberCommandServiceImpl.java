package com.example.umc8th.service.command.impl;

import com.example.umc8th.dto.MemberRequestDTO;
import com.example.umc8th.entity.Member;
import com.example.umc8th.repository.MemberRepository;
import com.example.umc8th.service.command.MemberCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member signUp(MemberRequestDTO.SignUpRequestDTO dto) {
        return memberRepository.save(
                Member.builder()
                        .username(dto.getUsername())
                        .password(passwordEncoder.encode(dto.getPassword()))
                        .build()
        );
    }
}