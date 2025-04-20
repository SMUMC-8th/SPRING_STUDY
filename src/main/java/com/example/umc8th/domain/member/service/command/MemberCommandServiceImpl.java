package com.example.umc8th.domain.member.service.command;

import com.example.umc8th.domain.member.converter.MemberConverter;
import com.example.umc8th.domain.member.dto.MemberRequestDTO;
import com.example.umc8th.domain.member.dto.MemberResponseDTO;
import com.example.umc8th.domain.member.entity.Member;
import com.example.umc8th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public MemberResponseDTO.SignUpResponseDTO signUp(MemberRequestDTO.SignUpRequestDTO dto) {
        String password = passwordEncoder.encode(dto.password());
        return MemberConverter.toSignUpResponseDTO(
                memberRepository.save(
                        MemberConverter.toMember(dto.username(), password)
                )
        );
    }
}
