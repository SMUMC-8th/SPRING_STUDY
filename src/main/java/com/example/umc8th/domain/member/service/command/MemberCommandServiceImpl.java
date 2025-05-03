package com.example.umc8th.domain.member.service.command;

import com.example.umc8th.domain.member.converter.MemberConverter;
import com.example.umc8th.domain.member.dto.request.MemberRequestDTO;
import com.example.umc8th.domain.member.dto.response.MemberResponseDTO;
import com.example.umc8th.domain.member.entity.Member;
import com.example.umc8th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberResponseDTO.SignUp signUp(MemberRequestDTO.SignUp reqDTO) {

        Member member = MemberConverter.toMember(reqDTO, passwordEncoder);
        Member savedMember = memberRepository.save(member);

        return MemberConverter.toSignUpResponseDTO(savedMember);
    }
}
