package com.example.umc8th.service.oauth;

import com.example.umc8th.dto.MemberResponseDTO;

public interface OAuth2Service {
    MemberResponseDTO.MemberTokenDTO login(String code);
}
