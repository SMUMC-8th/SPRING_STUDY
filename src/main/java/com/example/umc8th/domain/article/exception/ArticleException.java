package com.example.umc8th.domain.article.exception;


import com.example.umc8th.global.apiPayload.exception.CustomException;
import lombok.Getter;

@Getter
public class ArticleException extends CustomException {

    public ArticleException(ArticleErrorCode errorCode){
        super(errorCode);
    }
}
