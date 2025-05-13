package com.example.umc8th.domain.member.exception;

import com.example.umc8th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "멤버를 찾지 못했습니다."),
    BAD_CREDENTIAL(HttpStatus.UNAUTHORIZED, "MEMBER401_1", "권한이 없습니다."),
    OAUTH_TOKEN_FAIL(HttpStatus.BAD_REQUEST, "MEMBER400_1", "토큰 변환에 실패했습니다."),
    OAUTH_USER_INFO_FAIL(HttpStatus.NOT_FOUND, "MEMBER404_1", "사용자를 찾지 못했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


}





