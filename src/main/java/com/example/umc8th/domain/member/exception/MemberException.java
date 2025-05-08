package com.example.umc8th.domain.member.exception;

import com.example.umc8th.domain.article.exception.ArticleErrorCode;
import com.example.umc8th.global.apiPayload.exception.CustomException;
import lombok.Getter;

@Getter
public class MemberException extends CustomException {

    public MemberException(MemberErrorCode errorCode){
        super(errorCode);
    }
}
