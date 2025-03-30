package com.example.umc8th.global.apiPayload.exception;

import com.example.umc8th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class ArticleException extends RuntimeException {

    private final BaseErrorCode code;

    public ArticleException(BaseErrorCode code) {
        this.code = code;
    }
}
