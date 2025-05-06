package com.example.umc8th.global.apiPayload;

import com.example.umc8th.global.apiPayload.error.BaseErrorCode;
import com.example.umc8th.global.apiPayload.success.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
// 생성자로 객체를 생성하는 것을 막기
@AllArgsConstructor(access = AccessLevel.PRIVATE)
// json 형식으로 줄 때 어떤 순서로, 어떤 변수들을 줄것인지 결정하는 Annotation
@JsonPropertyOrder({"isSuccess","status", "code", "message", "result"})
public class CustomResponse<T> {

    @JsonProperty("isSuccess") // 변수 이름이 isSuccess 라는 것을 명시하기 위한 Annotation
    private boolean isSuccess;

    @JsonProperty("status")
    private HttpStatus status;

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private T result;


    public static <T> CustomResponse<T> onSuccess(BaseSuccessCode baseSuccessCode, T result) {
        return CustomResponse.<T>builder()
                .isSuccess(true)
                .status(baseSuccessCode.getStatus())
                .code(baseSuccessCode.getCode())
                .message(baseSuccessCode.getMessage())
                .build();
    }

    public static CustomResponse<?> onFailure(BaseErrorCode errorCode) {
        return CustomResponse.builder()
                .isSuccess(false)
                .status(errorCode.getStatus())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
    }

    public static <T> CustomResponse<T> onFailure(String code, String message, T result) {
        return CustomResponse.<T>builder()
                .isSuccess(false)
                .status(HttpStatus.UNAUTHORIZED) // 또는 매개변수로 받아도 됨
                .code(code)
                .message(message)
                .result(result)
                .build();
    }

}