package com.example.umc8th.global.jwt.exception;

import com.example.umc8th.domain.member.exception.MemberErrorCode;
import com.example.umc8th.global.apiPayload.exception.CustomException;
import lombok.Getter;

@Getter
public class SecurityException extends CustomException {

    public SecurityException(SecurityErrorCode errorCode){
        super(errorCode);
    }
}