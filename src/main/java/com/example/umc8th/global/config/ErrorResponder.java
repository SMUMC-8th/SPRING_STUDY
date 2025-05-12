package com.example.umc8th.global.config;

import com.example.umc8th.global.apiPayload.CustomResponse;
import com.example.umc8th.global.apiPayload.code.BaseErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ErrorResponder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendErrorResponse(HttpServletResponse response, BaseErrorCode errorCode) throws IOException {
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType("application/json; charset=UTF-8");

        CustomResponse<Object> errorResponse = CustomResponse.onFailure(
                errorCode.getCode(),
                errorCode.getMessage(),
                null
        );

        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}