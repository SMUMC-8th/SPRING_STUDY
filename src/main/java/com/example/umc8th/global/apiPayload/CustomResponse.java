package com.example.umc8th.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

// 생성자로 객체를 생성하는 것을 막기
@AllArgsConstructor(access = AccessLevel.PRIVATE)
// json 형식으로 줄 때 어떤 순서로, 어떤 변수들을 줄것인지 결정하는 Annotation
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class CustomResponse<T> {

    @JsonProperty("isSuccess") // 변수 이름이 isSuccess라는 것을 명시하기 위한 Annotation
    private boolean isSuccess;

    @JsonProperty("code")
    private HttpStatus code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private T result;


    // onSuccess
    public CustomResponse onSuccess(T result) {
        return new CustomResponse(true, HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), result);
    }

    // onFailure
    public CustomResponse onFailure(T result) {
        return new CustomResponse(false, HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), result);
    }

}
