package com.example.umc8th.exception;

import com.example.umc8th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "멤버를 찾지 못했습니다."),
    BAD_CREDENTIAL(HttpStatus.BAD_REQUEST, "MEMBER401", "유효하지 않는 토큰입니다."),

    OAUTH_TOKEN_FAIL(HttpStatus.MULTI_STATUS, "MEMBER2400", "OAuth 토큰을 변경하지 못했습니다."),
    OAUTH_USER_INFO_FAIL(HttpStatus.MULTI_STATUS, "MEMBER2500", "사용자 정보를 가져오지 못했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
