package com.example.umc8th.domain.article.exception;

import com.example.umc8th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ArticleErrorCode implements BaseErrorCode {

    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE404_0", "해당 게시글을 찾을 수 없습니다."),
    INVALID_CURSOR_FORMAT(HttpStatus.BAD_REQUEST, "ARTICLE400_0", "커서 형식이 올바르지 않습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
