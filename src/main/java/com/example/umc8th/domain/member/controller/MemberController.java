package com.example.umc8th.domain.member.controller;

import com.example.umc8th.domain.member.dto.request.MemberRequestDTO;
import com.example.umc8th.domain.member.dto.response.MemberResponseDTO;
import com.example.umc8th.domain.member.service.command.MemberCommandService;
import com.example.umc8th.global.apiPayload.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public CustomResponse<MemberResponseDTO.SignUp> signUp(@RequestBody MemberRequestDTO.SignUp reqDTO) {
        MemberResponseDTO.SignUp resDTO = memberCommandService.signUp(reqDTO);
        return CustomResponse.onSuccess(HttpStatus.CREATED, resDTO);
    }
}
