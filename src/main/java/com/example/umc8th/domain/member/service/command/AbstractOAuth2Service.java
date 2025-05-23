package com.example.umc8th.domain.member.service.command;

import com.example.umc8th.domain.member.dto.MemberResponseDTO;
import com.example.umc8th.domain.member.entity.Member;
import com.example.umc8th.domain.member.enums.SocialType;
import com.example.umc8th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractOAuth2Service implements OAuth2Service {

    private final MemberRepository memberRepository;
    private final TokenCommandService tokenCommandService;

    @Override
    public final MemberResponseDTO.LoginResponseDTO login(String provider, String code) {
        String token = getAccessToken(code);
        String email = getEmail(token);
        return loginOrSignup(SocialType.valueOf(provider.toUpperCase()), email);
    }

    public abstract String getAccessToken(String accessCode);

    public abstract String getEmail(String accessToken);

    private MemberResponseDTO.LoginResponseDTO loginOrSignup(SocialType socialType, String email) {
        Member member;
        Optional<Member> optional = memberRepository.findByEmail(email);
        member = optional.orElseGet(() -> memberRepository.save(Member.builder()
                .email(email)
                .password("")
                .socialType(socialType)
                .role("ROLE_USER")
                .build()));


        return tokenCommandService.createLoginToken(member);
    }

}
