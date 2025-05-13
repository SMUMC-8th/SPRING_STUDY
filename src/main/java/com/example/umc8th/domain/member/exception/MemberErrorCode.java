package com.example.umc8th.domain.member.exception;

import com.example.umc8th.global.apiPayload.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "멤버를 찾지 못했습니다."),
    BAD_CREDENTIAL(HttpStatus.BAD_REQUEST, "MEMBER401", "유효하지 않는 토큰입니다."),
    OAUTH_TOKEN_FAIL(HttpStatus.BAD_REQUEST, "MEMBER4001", "토큰 변환 실패"),
    OAUTH_EMAIL_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4002", "이메일 정보를 찾을 수 없습니다."),
    OAUTH_LOGIN_FAIL(HttpStatus.UNAUTHORIZED, "MEMBER4011", "로그인에 실패하였습니다."),
    OAUTH_USER_INFO_FAIL(HttpStatus.NOT_FOUND, "MEMBER4004", "사용자 정보를 가져오는데 실패하였습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}