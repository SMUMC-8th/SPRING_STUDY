package com.example.umc8th.service.command;

import com.example.umc8th.entity.Member;
import com.example.umc8th.dto.MemberResponseDTO;

public interface TokenCommandService {
    MemberResponseDTO.LoginResponseDTO createLoginToken(Member member);
}
