package com.example.umc8th.domain.member.exception;

import com.example.umc8th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "사용자를 찾지 못했습니다."),
    BAD_CREDENTIAL(HttpStatus.BAD_REQUEST, "MEMBER400", "비밀번호가 틀렸습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
