package com.example.umc8th.global.apiPayload;

import com.example.umc8th.global.apiPayload.code.GeneralSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class GlobalResponse<T> {

    @JsonProperty("isSuccess")
    private final boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;

    public static <T> GlobalResponse<T> ok(T result) {
        return GlobalResponse.onSuccess(
                GeneralSuccessCode.OK_200.getCode(),
                GeneralSuccessCode.OK_200.getMessage(),
                result
        );
    }

    public static <T> GlobalResponse<T> created(T result) {
        return GlobalResponse.onSuccess(
                GeneralSuccessCode.CREATED_201.getCode(),
                GeneralSuccessCode.CREATED_201.getMessage(),
                result
        );
    }

    public static <T> GlobalResponse<T> onSuccess(String code, String message, T result) {
        return new GlobalResponse<>(true, code, message, result);
    }

    public static <T> GlobalResponse<T> onFailure(String code, String message) {
        return onFailure(code, message, null);
    }

    // 실패시 result는 null로 반환
    public static <T> GlobalResponse<T> onFailure(String code, String message, T result) {
        return new GlobalResponse<>(false, code, message, result);
    }
}
