package com.example.umc8th.domain.member.exception;

import com.example.umc8th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "사용자를 찾지 못했습니다."),
    BAD_CREDENTIAL(HttpStatus.BAD_REQUEST, "MEMBER400", "비밀번호가 틀렸습니다."),

    OAUTH_TOKEN_FAIL(HttpStatus.UNAUTHORIZED, "MEMBER401", "인가코드로 토큰을 가져오는데 실패했습니다."),
    OAUTH_USER_INFO_FAIL(HttpStatus.UNAUTHORIZED, "MEMBER401", "토큰으로 사용자 정보를 가져오는 데 실패했습니다."),
    UNSUPPORTED_OAUTH_TYPE(HttpStatus.BAD_REQUEST, "MEMBER400", "지원하지 않는 소셜 로그인입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
