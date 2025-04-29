package com.example.umc8th.global.auth.handler;

import com.example.umc8th.global.apiPayload.CustomResponse;
import com.example.umc8th.global.apiPayload.code.BaseErrorCode;
import com.example.umc8th.global.apiPayload.code.GeneralErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        BaseErrorCode code = GeneralErrorCode.FORBIDDEN_403;
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(code.getHttpStatus().value());

        CustomResponse<Object> errorResponse = CustomResponse.onFailure(
                code.getCode(),
                code.getMessage(),
                null
        );

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
