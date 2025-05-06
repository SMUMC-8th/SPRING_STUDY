package com.example.umc8th.domain.member.service.command;

import com.example.umc8th.domain.member.dto.request.MemberRequestDTO;
import com.example.umc8th.domain.member.dto.response.MemberResponseDTO;
import com.example.umc8th.domain.member.entity.Member;

public interface MemberCommandService {
    MemberResponseDTO.LoginResponseDTO login(MemberRequestDTO.LoginRequestDTO dto);
    Member signUp(MemberRequestDTO.SignUpRequestDTO dto);
}
