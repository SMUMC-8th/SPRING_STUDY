package com.example.umc8th.global.apiPayload.success;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode{

    // 200
    OK(HttpStatus.OK, "200", "요청에 성공했습니다."),

    // 201
    CREATED(HttpStatus.CREATED, "201", "자원이 생성되었습니다."),

    // 204
    DELETED(HttpStatus.NO_CONTENT, "204", "성공적으로 삭제되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
