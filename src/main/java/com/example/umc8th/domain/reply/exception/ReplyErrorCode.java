package com.example.umc8th.domain.reply.exception;

import com.example.umc8th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ReplyErrorCode implements BaseErrorCode {

    UNAUTHORIZED_401(HttpStatus.UNAUTHORIZED,
            "Reply401",
            "인증되지 않았습니다."),
    FORBIDDEN_403(HttpStatus.FORBIDDEN,
            "Reply403",
            "게시물의 댓글이 아닙니다."),
    NOT_FOUND_404(HttpStatus.NOT_FOUND,
            "Reply404",
            "댓글을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
