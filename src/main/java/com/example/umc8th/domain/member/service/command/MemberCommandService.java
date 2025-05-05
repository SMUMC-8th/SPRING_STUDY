package com.example.umc8th.domain.member.service.command;

import com.example.umc8th.domain.member.dto.request.MemberRequestDTO;
import com.example.umc8th.domain.member.entity.Member;

public interface MemberCommandService {
    Member signUp(MemberRequestDTO.SignUpRequestDTO dto);
}
