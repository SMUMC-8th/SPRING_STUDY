package com.example.umc8th.global.config;

import com.example.umc8th.global.apiPayload.CustomResponse;
import com.example.umc8th.global.apiPayload.error.BaseErrorCode;
import com.example.umc8th.global.apiPayload.error.GeneralErrorCode;
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
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        BaseErrorCode code = GeneralErrorCode.UNAUTHORIZED_401;
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(code.getStatus().value());

        CustomResponse<Object> errorResponse = CustomResponse.onFailure(
                code.getCode(),
                code.getMessage(),
                null
        );

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
