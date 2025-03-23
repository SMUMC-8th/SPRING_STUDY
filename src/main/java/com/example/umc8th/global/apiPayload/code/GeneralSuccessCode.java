package com.example.umc8th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK_200(HttpStatus.OK,
            "COMMON200",
            "정상적으로 처리했습니다."),
    CREATED_201(HttpStatus.CREATED,
            "COMMON201",
            "생성요청을 받아 새로운 데이터를 생성했습니다."),
    ACCEPTED_202(HttpStatus.ACCEPTED,
            "COMMON202",
            "요청을 받았지만 아직 처리하지 않았습니다."),
    NO_CONTENT_204(HttpStatus.NO_CONTENT,
            "COMMON204",
            "성공적으로 처리했지만 콘텐츠는 제공하지 않습니다."),
    PARTIAL_CONTENT_206(HttpStatus.PARTIAL_CONTENT,
            "COMMON206",
            "콘텐츠의 일부분만 제공합니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
