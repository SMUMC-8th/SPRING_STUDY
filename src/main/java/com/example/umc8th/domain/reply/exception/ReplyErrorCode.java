package com.example.umc8th.domain.reply.exception;

import com.example.umc8th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReplyErrorCode implements BaseErrorCode {

    REPLY_NOT_FOUND(HttpStatus.NOT_FOUND, "REPLY404_0", "해당 댓글을 찾을 수 없습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
