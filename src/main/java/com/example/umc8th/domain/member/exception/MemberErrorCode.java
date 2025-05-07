package com.example.umc8th.domain.member.exception;

import com.example.umc8th.global.apiPayload.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "멤버를 찾지 못했습니다."),
    BAD_CREDENTIAL(HttpStatus.BAD_REQUEST, "MEMBER401", "유효하지 않는 토큰입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}