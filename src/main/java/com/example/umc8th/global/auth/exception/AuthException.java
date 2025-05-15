package com.example.umc8th.global.auth.exception;

import com.example.umc8th.global.apiPayload.exception.GeneralException;

public class AuthException extends GeneralException {
    public AuthException(AuthErrorCode code) {
        super(code);
    }
}