package com.example.umc8th.global.apiPayload.exception.handler;

import com.example.umc8th.global.apiPayload.GlobalResponse;
import com.example.umc8th.global.apiPayload.code.BaseErrorCode;
import com.example.umc8th.global.apiPayload.exception.ArticleException;
import com.example.umc8th.global.apiPayload.exception.GeneralException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ExceptionAdvice {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<?> generalException(GeneralException e) {
        log.warn("General exception: {}", e.getCode().getMessage());
        BaseErrorCode code = e.getCode();
        GlobalResponse<String> response = GlobalResponse.onFailure(code.getCode(), code.getMessage());
        return ResponseEntity.status(code.getStatus()).body(response);
    }
    @ExceptionHandler(ArticleException.class)
    public ResponseEntity<?> articleException(ArticleException e) {
        log.warn("Article exception: {}", e.getCode().getMessage());
        BaseErrorCode code = e.getCode();
        GlobalResponse<String> response = GlobalResponse.onFailure(code.getCode(), code.getMessage());
        return ResponseEntity.status(code.getStatus()).body(response);
    }
}
