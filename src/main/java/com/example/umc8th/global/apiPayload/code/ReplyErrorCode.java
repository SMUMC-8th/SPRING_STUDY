package com.example.umc8th.global.apiPayload.code;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReplyErrorCode implements BaseErrorCode{

    REPLY_NOT_FOUND(HttpStatus.NOT_FOUND,"REPLY404", "댓글이 존재하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
