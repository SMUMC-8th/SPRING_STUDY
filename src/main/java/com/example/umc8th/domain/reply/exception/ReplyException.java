package com.example.umc8th.domain.reply.exception;

import com.example.umc8th.global.apiPayload.exception.CustomException;
import lombok.Getter;

@Getter
public class ReplyException extends CustomException {

    public ReplyException(ReplyErrorCode errorCode){
        super(errorCode);
    }
}
