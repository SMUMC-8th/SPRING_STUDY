package com.example.umc8th.global.exception;

import com.example.umc8th.global.apiPayload.exception.GeneralException;

public class WebClientException extends GeneralException {
    public WebClientException(WebClientErrorCode code) {
        super(code);
    }
}
