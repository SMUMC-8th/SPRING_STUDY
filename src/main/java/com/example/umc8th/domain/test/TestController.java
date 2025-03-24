package com.example.umc8th.domain.test;

import com.example.umc8th.global.apiPayload.GlobalResponse;
import com.example.umc8th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name="테스트용 API")
public class TestController {

    @GetMapping("/test")
    @Operation(summary = "테스트하는 API", description = "void -> 'HTTP 상태 코드'")
    public GlobalResponse<String> test() {
        return GlobalResponse.onSuccess("testing");
    }
}
