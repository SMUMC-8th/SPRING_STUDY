package com.example.umc8th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public interface BaseErrorCode {

    HttpStatus getStatus();
    String getCode();
    String getMessage();
    // 위에서는 status, code, message를 가져오는 method만 있는데 직접 만든 응답을 반환하는 코드가 더 좋을 수 있어요.
}

