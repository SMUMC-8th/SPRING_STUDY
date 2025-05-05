package com.example.umc8th.controller;

import com.example.umc8th.dto.MemberRequestDTO;
import com.example.umc8th.dto.MemberResponseDTO;
import com.example.umc8th.entity.Member;
import com.example.umc8th.global.apiPayload.CustomResponse;
import com.example.umc8th.service.command.MemberCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/sign-up")
    public CustomResponse<MemberResponseDTO.SignUpResponseDTO> signUp(@RequestBody MemberRequestDTO.SignUpRequestDTO dto) {
        Member member = memberCommandService.signUp(dto);
        return CustomResponse.ok(MemberResponseDTO.SignUpResponseDTO.from(member));
    }
}