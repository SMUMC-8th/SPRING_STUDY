package com.example.umc8th.service.command;

import com.example.umc8th.dto.MemberRequestDTO;
import com.example.umc8th.dto.MemberResponseDTO;
import com.example.umc8th.entity.Member;

public interface MemberCommandService {
    Member signUp(MemberRequestDTO.SignUpRequestDTO dto);
    MemberResponseDTO.LoginResponseDTO login(MemberRequestDTO.LoginRequestDTO dto);

}
