package com.example.umc8th.domain.reply.exception;


import com.example.umc8th.global.apiPayload.exception.GeneralException;

public class ReplyException extends GeneralException {

    public ReplyException(ReplyErrorCode code) {
        super(code);
    }
}
