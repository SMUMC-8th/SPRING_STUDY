package com.example.umc8th.domain.test.controller;

import com.example.umc8th.global.apiPayload.CustomResponse;
import com.example.umc8th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc8th.global.apiPayload.exception.GeneralException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "테스트 API")
public class TestController {

    @GetMapping("/test")
    @Operation(summary = "성공 테스트", description = "테스트용 컨트롤러입니다.")
    public CustomResponse<String> successTest() {
        return CustomResponse.ok("Hello World");
    }

    @GetMapping("/error")
    @Operation(summary = "에러 테스트")
    public CustomResponse<String> errorTest() {
        throw new GeneralException(GeneralErrorCode.BAD_REQUEST_400);
    }
}
