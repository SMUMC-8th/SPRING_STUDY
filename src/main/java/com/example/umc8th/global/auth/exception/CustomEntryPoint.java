package com.example.umc8th.global.auth.exception;

import com.example.umc8th.global.apiPayload.GlobalResponse;
import com.example.umc8th.global.apiPayload.code.BaseErrorCode;
import com.example.umc8th.global.apiPayload.code.GeneralErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws ServletException, IOException {

        BaseErrorCode code = GeneralErrorCode.UNAUTHORIZED_401;
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(code.getStatus().value());

        GlobalResponse<Object> errorResponse = GlobalResponse.onFailure(
                code.getCode(),
                code.getMessage()
        );

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), errorResponse);
    }
}