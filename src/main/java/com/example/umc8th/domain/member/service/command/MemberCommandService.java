package com.example.umc8th.domain.member.service.command;

import com.example.umc8th.domain.member.dto.request.MemberRequestDTO;
import com.example.umc8th.domain.member.dto.response.MemberResponseDTO;

public interface MemberCommandService {

    MemberResponseDTO.SignUp signUp(MemberRequestDTO.SignUp reqDTO);

}
