package com.example.umc8th.global.apiPayload.handler;

import com.example.umc8th.global.apiPayload.CustomResponse;
import com.example.umc8th.global.apiPayload.code.BaseErrorCode;

import com.example.umc8th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc8th.global.apiPayload.code.GeneralException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    // 컨트롤러 메서드에서 @Valid 어노테이션을 사용하여 DTO의 유효성 검사를 수행
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<CustomResponse<Map<String, String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        // 검사에 실패한 필드와 그에 대한 메시지를 저장하는 Map
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        BaseErrorCode validationErrorCode = GeneralErrorCode.VALIDATION_FAILED; // BaseErrorCode로 통일
        CustomResponse<Map<String, String>> errorResponse = CustomResponse.onFailure(
                validationErrorCode.getCode(),
                validationErrorCode.getMessage(),
                errors
        );
        // 에러 코드, 메시지와 함께 errors를 반환
        return ResponseEntity.status(validationErrorCode.getHttpStatus()).body(errorResponse);
    }

    // ConstraintViolationException 핸들러
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<CustomResponse<Map<String, String>>> handleConstraintViolationException(
            ConstraintViolationException ex) {
        // 제약 조건 위반 정보를 저장할 Map
        Map<String, String> errors = new HashMap<>();

        ex.getConstraintViolations().forEach(violation -> {
            String propertyPath = violation.getPropertyPath().toString();
            // 마지막 필드명만 추출 (예: user.name -> name)
            String fieldName = propertyPath.contains(".") ?
                    propertyPath.substring(propertyPath.lastIndexOf(".") + 1) : propertyPath;

            errors.put(fieldName, violation.getMessage());
        });

        BaseErrorCode constraintErrorCode = GeneralErrorCode.VALIDATION_FAILED;
        CustomResponse<Map<String, String>> errorResponse = CustomResponse.onFailure(
                constraintErrorCode.getCode(),
                constraintErrorCode.getMessage(),
                errors
        );

        log.warn("[ ConstraintViolationException ]: Constraint violations detected");

        return ResponseEntity.status(constraintErrorCode.getHttpStatus()).body(errorResponse);
    }

    //애플리케이션에서 발생하는 커스텀 예외를 처리
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<CustomResponse<Void>> handleCustomException(GeneralException ex) {
        //예외가 발생하면 로그 기록
        log.warn("[ CustomException ]: {}", ex.getCode().getMessage());

        BaseErrorCode code = ex.getCode();
        //커스텀 예외에 정의된 에러 코드와 메시지를 포함한 응답 제공
        return ResponseEntity.status(code.getHttpStatus())
                .body(CustomResponse.onFailure(code.getCode(), ex.getMessage()));
    }

    // 그 외의 정의되지 않은 모든 예외 처리
    @ExceptionHandler({Exception.class})
    public ResponseEntity<CustomResponse<String>> handleAllException(Exception ex) {
        log.error("[WARNING] Internal Server Error : {} ", ex.getMessage());
        BaseErrorCode errorCode = GeneralErrorCode.INTERNAL_SERVER_ERROR_500;
        CustomResponse<String> errorResponse = CustomResponse.onFailure(
                errorCode.getCode(),
                errorCode.getMessage(),
                null
        );
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(errorResponse);
    }
}