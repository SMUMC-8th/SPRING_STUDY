package com.example.umc8th.global.jwt.exception;

import com.example.umc8th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SecurityErrorCode implements BaseErrorCode {

    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "SEC400_0", "잘못된 토큰입니다."),
    BLACKLIST_TOKEN(HttpStatus.BAD_REQUEST, "SEC400_1", "블랙리스트로 등록된 토큰입니다."),

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "SEC401_0", "인증되지 않은 회원입니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "SEC401_1", "토큰이 만료되었습니다."),
    BAD_CREDENTIALS(HttpStatus.UNAUTHORIZED, "SEC401_2", "잘못된 인증 정보입니다."),

    FORBIDDEN(HttpStatus.FORBIDDEN, "SEC403_0", "접근 권한이 없습니다."),

    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER404_0", "존재하지 않는 계정입니다. 회원가입 후 로그인해주세요."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST, "SEC404_0", "리프레시 토큰이 존재하지 않습니다."),

    INTERNAL_SECURITY_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SEC500_0", "서버에서 인증 처리 중 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}

