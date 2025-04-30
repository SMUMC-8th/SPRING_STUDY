package com.example.umc8th.domain.member.exception.code;

import com.example.umc8th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND_404(HttpStatus.NOT_FOUND,
            "Member404",
            "사용자를 찾을 수 없습니다."),
    BAD_REQUEST_400(HttpStatus.BAD_REQUEST,
            "Member400",
            "아이디나 비밀번호가 잘못되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
