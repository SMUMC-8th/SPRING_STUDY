package com.example.umc8th.global.apiPayload.exception.handler;

import com.example.umc8th.global.apiPayload.GlobalResponse;
import com.example.umc8th.global.apiPayload.code.BaseErrorCode;
import com.example.umc8th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc8th.global.apiPayload.exception.GeneralException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
// 이슈 발생: ControllerAdvice와 Swagger간 충돌 발생
//@RestControllerAdvice(annotations = RestController.class)
public class ExceptionAdvice {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<GlobalResponse<String>> generalException(GeneralException e) {
        log.warn("General exception: {}", e.getCode().getMessage());
        BaseErrorCode code = e.getCode();
        GlobalResponse<String> response = GlobalResponse.onFailure(code.getCode(), code.getMessage());
        return ResponseEntity.status(code.getStatus()).body(response);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponse<String>> exception(Exception e) {
        log.error("Internal Server Error: {}", e.getMessage());
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR_500;
        GlobalResponse<String> response = GlobalResponse.onFailure(code.getCode(), code.getMessage());
        return ResponseEntity.status(code.getStatus()).body(response);
    }
}
