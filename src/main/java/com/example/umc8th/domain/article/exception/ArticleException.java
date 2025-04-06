package com.example.umc8th.domain.article.exception;

import com.example.umc8th.global.apiPayload.exception.GeneralException;

public class ArticleException extends GeneralException {
    public ArticleException(ArticleErrorCode code) {
        super(code);
    }
}
