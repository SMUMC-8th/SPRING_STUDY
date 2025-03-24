package com.example.umc8th.global.apiPayload;

import com.example.umc8th.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class CustomResponse<T> {

    @JsonProperty("isSuccess")
    private HttpStatus httpStatus;

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    private T result;

    public static CustomResponse onSuccess() {
        return new CustomResponse(
                HttpStatus.OK,
                "SUCCESS",
                HttpStatus.OK.getReasonPhrase()
                ,HttpStatus.OK
        );
    }

    public static <T> CustomResponse<T> of(BaseSuccessCode code, T result) {
        return new CustomResponse<>(
                code.getStatus(),
                code.getCode(),
                code.getMessage(),
                result
        );
    }
}
