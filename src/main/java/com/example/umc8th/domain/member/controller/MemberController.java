package com.example.umc8th.domain.member.controller;

import com.example.umc8th.domain.member.dto.request.MemberRequestDTO;
import com.example.umc8th.domain.member.dto.response.MemberResponseDTO;
import com.example.umc8th.domain.member.entity.Member;
import com.example.umc8th.domain.member.service.command.MemberCommandService;
import com.example.umc8th.domain.member.service.command.OAuth2Service;
import com.example.umc8th.global.apiPayload.CustomResponse;
import com.example.umc8th.global.apiPayload.success.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final OAuth2Service oAuth2Service;

    @PostMapping("/sign-up")
    public CustomResponse<MemberResponseDTO.SignUpResponseDTO> signUp(@RequestBody MemberRequestDTO.SignUpRequestDTO dto) {
        Member member = memberCommandService.signUp(dto);

        return CustomResponse.onSuccess(GeneralSuccessCode.OK, MemberResponseDTO.SignUpResponseDTO.from(member));
    }

    @GetMapping("/login")
    public CustomResponse<MemberResponseDTO.LoginResponseDTO> login(@RequestBody MemberRequestDTO.LoginRequestDTO dto) {
        MemberResponseDTO.LoginResponseDTO member = memberCommandService.login(dto);

        return CustomResponse.onSuccess(GeneralSuccessCode.OK, member);
    }
}
