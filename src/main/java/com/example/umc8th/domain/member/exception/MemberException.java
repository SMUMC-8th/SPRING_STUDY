package com.example.umc8th.domain.member.exception;

import com.example.umc8th.global.apiPayload.exception.GeneralException;

public class MemberException extends GeneralException {

    public MemberException(MemberErrorCode code) {
        super(code);
    }
}
