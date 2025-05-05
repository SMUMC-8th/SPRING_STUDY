package com.example.umc8th.code.member.service;

import com.example.umc8th.code.member.entity.Member;
import com.example.umc8th.code.member.dto.MemberRequestDTO;
import com.example.umc8th.code.member.dto.MemberResponseDTO;

public interface MemberCommandService {

    Member signUp(MemberRequestDTO.SignUpRequestDTO dto);
    MemberResponseDTO.LoginResponseDTO login(MemberRequestDTO.LoginRequestDTO dto);
}
