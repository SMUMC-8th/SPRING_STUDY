package com.example.umc8th.article.exception;

import com.example.umc8th.article.exception.code.ArticleErrorCode;
import com.example.umc8th.global.apiPayload.exception.GeneralException;
import lombok.Getter;

@Getter
public class ArticleException extends GeneralException {
    public ArticleException(ArticleErrorCode code) {
        super(code);
    }
}
