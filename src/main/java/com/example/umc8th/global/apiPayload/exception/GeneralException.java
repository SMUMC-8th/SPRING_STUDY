package com.example.umc8th.global.apiPayload.exception;

import com.example.umc8th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException{

    // 예외에서 발생한 에러의 상세 내용
    private final BaseErrorCode code;

    // 생성자
    public GeneralException(BaseErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }

}