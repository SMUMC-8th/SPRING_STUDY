package com.example.umc8th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum GeneralSuccessCode implements BaseSuccessCode {
    SUCCESS(HttpStatus.OK,
            "SUCCESS",
            "요청이 성공적으로 처리되었습니다");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
