package com.example.umc8th.domain.member.controller;

import com.example.umc8th.domain.member.dto.MemberRequestDTO;
import com.example.umc8th.domain.member.dto.MemberResponseDTO;
import com.example.umc8th.domain.member.service.command.MemberCommandService;
import com.example.umc8th.global.apiPayload.GlobalResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "유저API")
public class MemberController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/sign-up")
    public GlobalResponse<MemberResponseDTO.SignUpResponseDTO> signUp(
            @RequestBody MemberRequestDTO.SignUpRequestDTO dto
    ){
        return GlobalResponse.ok(memberCommandService.signUp(dto));
    }
}
