package com.example.umc8th.global.apiPayload.code;

import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {
    private final BaseErrorCode code;
    public GeneralException(BaseErrorCode code) {
        this.code = code;
    }
}
