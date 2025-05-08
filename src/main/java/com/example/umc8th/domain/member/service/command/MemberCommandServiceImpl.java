package com.example.umc8th.domain.member.service.command;

import com.example.umc8th.domain.member.converter.MemberConverter;
import com.example.umc8th.domain.member.dto.request.MemberRequestDTO;
import com.example.umc8th.domain.member.dto.response.MemberResponseDTO;
import com.example.umc8th.domain.member.entity.Member;
import com.example.umc8th.domain.member.exception.MemberErrorCode;
import com.example.umc8th.domain.member.exception.MemberException;
import com.example.umc8th.domain.member.repository.MemberRepository;
import com.example.umc8th.global.jwt.dto.JwtDTO;
import com.example.umc8th.global.jwt.exception.SecurityErrorCode;
import com.example.umc8th.global.jwt.exception.SecurityException;
import com.example.umc8th.global.jwt.service.TokenCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final TokenCommandService tokenCommandService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberResponseDTO.SignUp signUp(MemberRequestDTO.SignUp reqDTO) {
        Member member = MemberConverter.toMember(reqDTO, passwordEncoder);
        Member savedMember = memberRepository.save(member);

        return MemberConverter.toSignUpResponseDTO(savedMember);
    }

    @Override
    public JwtDTO login(MemberRequestDTO.login reqDTO) {
        Member member = memberRepository.findByUsername(reqDTO.username())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(reqDTO.password(), member.getPassword())) {
            throw new SecurityException(SecurityErrorCode.BAD_CREDENTIALS);
        }

        return tokenCommandService.createJwtToken(member);
    }
}
