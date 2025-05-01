package com.example.umc8th.domain.member.service.command;

import com.example.umc8th.domain.member.converter.MemberConverter;
import com.example.umc8th.domain.member.dto.MemberRequestDTO;
import com.example.umc8th.domain.member.dto.MemberResponseDTO;
import com.example.umc8th.domain.member.entity.Member;
import com.example.umc8th.domain.member.exception.MemberException;
import com.example.umc8th.domain.member.exception.code.MemberErrorCode;
import com.example.umc8th.domain.member.repository.MemberRepository;
import com.example.umc8th.global.auth.service.command.TokenCommandService;
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
    public MemberResponseDTO.SignUpResponseDTO signUp(MemberRequestDTO.SignUpRequestDTO dto) {
        String password = passwordEncoder.encode(dto.password());
        return MemberConverter.toSignUpResponseDTO(
                memberRepository.save(
                        MemberConverter.toMember(dto.username(), password)
                )
        );
    }

    @Override
    public MemberResponseDTO.LoginResponseDTO login(MemberRequestDTO.LoginRequestDTO dto) {
        Member member = memberRepository.findByUsername(dto.username()).orElseThrow(() ->
                new MemberException(MemberErrorCode.NOT_FOUND_404));
        if (!passwordEncoder.matches(dto.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.BAD_REQUEST_400);
        }
        return tokenCommandService.createLoginToken(member); // 유저 정보로 토큰만들기, 참고로 DTO에는 id, accessToken, refreshToken이 존재합니다.
    }
}
