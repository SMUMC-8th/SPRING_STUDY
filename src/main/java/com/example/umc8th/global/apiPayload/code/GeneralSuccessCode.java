package com.example.umc8th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "COMMON200", "성공적으로 처리했습니다."),
    CREATED(HttpStatus.CREATED, "COMMON201", "성공적으로 생성했습니다."),
    NO_CONTENT_204(HttpStatus.NO_CONTENT, "COMMON204", "성공했지만 콘텐츠는 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
