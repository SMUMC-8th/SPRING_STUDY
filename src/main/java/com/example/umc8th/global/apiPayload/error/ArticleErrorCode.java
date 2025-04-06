package com.example.umc8th.global.apiPayload.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
// enum 은 상수마다 필드를 초기화하기 위해 생성자 필수
@AllArgsConstructor
public enum ArticleErrorCode implements BaseErrorCode {

    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND,"ARTICLE404", "게시글이 존재하지 않습니다."),
    INVALID_ARTICLE_CONTENT(HttpStatus.BAD_REQUEST, "ARTICLE400", "게시글 내용이 유효하지 않습니다.");

    // 외부에서 사용하기 위해 Getter 필요
    private final HttpStatus status;
    private final String code;
    private final String message;
}
