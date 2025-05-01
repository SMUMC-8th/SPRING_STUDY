package com.example.umc8th.global.auth.service.command;

import com.example.umc8th.domain.member.dto.MemberResponseDTO;
import com.example.umc8th.domain.member.entity.Member;

public interface TokenCommandService {

    MemberResponseDTO.LoginResponseDTO createLoginToken(Member member);
}
