package com.example.umc8th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ArticleErrorCode implements BaseErrorCode {
    UNAUTHORIZED_401(HttpStatus.UNAUTHORIZED,
            "Article401",
            "작성자가 아닙니다."),
    FORBIDDEN_403(HttpStatus.FORBIDDEN,
            "Article403",
            "접근이 제한되었습니다."),
    NOT_FOUND_404(HttpStatus.NOT_FOUND,
            "Article404",
            "게시물을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
