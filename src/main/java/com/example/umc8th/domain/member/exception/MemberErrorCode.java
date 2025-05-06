package com.example.umc8th.domain.member.exception;

import com.example.umc8th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "멤버를 찾지 못했습니다."),
    BAD_CREDENTIAL(HttpStatus.UNAUTHORIZED, "MEMBER401", "권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


}





