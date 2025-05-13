package com.example.umc8th.domain.member.controller;

import com.example.umc8th.domain.member.dto.MemberRequestDTO;
import com.example.umc8th.domain.member.dto.MemberResponseDTO;
import com.example.umc8th.domain.member.entity.Member;
import com.example.umc8th.domain.member.service.OAuth2ServiceImpl;
import com.example.umc8th.domain.member.service.command.MemberCommandService;
import com.example.umc8th.global.apiPayload.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final OAuth2ServiceImpl oAuth2Service;

    @PostMapping("/auth/sign-up")
    public CustomResponse<MemberResponseDTO.SignUpResponseDTO> signUp(@RequestBody MemberRequestDTO.SignUpRequestDTO dto) {
        Member member = memberCommandService.signUp(dto);
        return CustomResponse.ok(MemberResponseDTO.SignUpResponseDTO.from(member));
    }

    @PostMapping("/auth/login")
    public CustomResponse<MemberResponseDTO.LoginResponseDTO> login(
            @RequestBody MemberRequestDTO.LoginRequestDTO dto
    ){
        return CustomResponse.ok(memberCommandService.login(dto));
    }

    @GetMapping("/oauth2/callback/kakao")
    // queryParam 형식으로 코드를 받을 예정이니 RequestParam을 설정해줍니다
    // 응답은 저희 서버에 로그인 다 한 뒤에 토큰을 제공할 예정이니 TokenDTO로 설정해줍니다.
    public CustomResponse<MemberResponseDTO.MemberTokenDTO> loginWithKakao(@RequestParam("code") String code) {
        // 로직 구현 필요
        // 서비스 생성 이후

        return CustomResponse.ok(oAuth2Service.login(code));
    }


}