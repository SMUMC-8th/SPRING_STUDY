package com.example.umc8th.global.auth.exception.code;

import com.example.umc8th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements BaseErrorCode {

    OAUTH_TOKEN_FAIL(HttpStatus.INTERNAL_SERVER_ERROR,
            "OAUTH500",
            "토큰을 발급받을 수 없습니다."),
    OAUTH_USER_INFO_FAIL(HttpStatus.INTERNAL_SERVER_ERROR,
            "OAUTH400",
            "사용자 정보를 가져오는데 실패했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
