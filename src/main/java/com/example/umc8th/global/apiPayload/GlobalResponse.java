package com.example.umc8th.global.apiPayload;

import com.example.umc8th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc8th.global.apiPayload.code.GeneralSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

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
        return GlobalResponse.onSuccess(GeneralSuccessCode.OK_200,result);
    }
    public static <T> GlobalResponse<T> onSuccess(GeneralSuccessCode code, T result) {
        return new GlobalResponse<>(true, code.getCode(), code.getMessage(), result);
    }
    public static <T> GlobalResponse<T> onFailure(String code, String message) {
        return onFailure(code, message, null);
    }
    // 실패시 result는 null로 반환
    public static <T> GlobalResponse<T> onFailure(String code, String message, T result) {
        return new GlobalResponse<>(false, code, message, result);
    }
}
