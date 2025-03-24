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
    private final HttpStatus httpStatus;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;

    public static <T> GlobalResponse<T> onSuccess(T result) {
        return new GlobalResponse<>(
                GeneralSuccessCode.OK_200.getStatus(),
                GeneralSuccessCode.OK_200.getCode(),
                GeneralSuccessCode.OK_200.getMessage(),
                null
        );
    }

    public static <T> GlobalResponse<T> onSuccess(GeneralSuccessCode code, T result) {
        return new GlobalResponse<>(code.getStatus(), code.getCode(), code.getMessage(), result);
    }
    // 실패시 result는 null로 반환
    public static <T> GlobalResponse<T> onFailure(GeneralErrorCode code) {
        return new GlobalResponse<>(code.getStatus(), code.getCode(), code.getMessage(), null);
    }
}
