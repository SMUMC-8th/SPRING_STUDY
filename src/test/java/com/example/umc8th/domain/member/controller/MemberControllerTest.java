package com.example.umc8th.domain.member.controller;

import com.example.umc8th.domain.member.dto.MemberRequestDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
// 테스트 후 DB RollBack
@Transactional
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName("기능 테스트")
    class LogicTest {

        @Test
        @DisplayName("회원가입이 정상적으로 작동하는가")
        void signUp() throws Exception{
            // given : username, password
            String json = objectMapper.writeValueAsString(
                    new MemberRequestDTO.SignUpRequestDTO("testing", "qwer")
            );
            // when : 회원가입 시도할 때
            mockMvc.perform(
                    post("/auth/sign-up")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
            )
            // then : HTTP 200
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("로그인이 정상적으로 작동하는가")
        void login() throws Exception {
            // given : username, password
            String json = objectMapper.writeValueAsString(
                    new MemberRequestDTO.LoginRequestDTO("test", "1234")
            );
            // when : 로그인 시도할 때
            mockMvc.perform(
                    post("/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
            )
            // then : HTTP 200, AccessToken, RefreshToken 모두 존재해야 함.
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.result.accessToken").exists())
                    .andExpect(jsonPath("$.result.refreshToken").exists());
        }

    }

    @Nested
    @DisplayName("인증 테스트")
    class AuthTest {

        @Test
        @DisplayName("미인증 상태에서 API 요청")
        void NotAuthButRequestAPI () throws Exception {
            // given : Nothing
            // when : 토큰 없이 요청을 보낼때
            mockMvc.perform(
                    get("/articles/1")
                            .accept(MediaType.APPLICATION_JSON)
            )
            // then : 401 Unauthorized
                    .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName("인증 상태에서 API 요청")
        void AuthRequestAPI () throws Exception {

            // given : 테스트 용 AccessToken 발급
            String json = objectMapper.writeValueAsString(
                    new MemberRequestDTO.SignUpRequestDTO("test", "1234")
            );
            MvcResult result = mockMvc.perform(
                            post("/auth/login")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(json)
                    )
                    .andExpect(status().isOk())
                    .andReturn();
            JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
            String accessToken = jsonNode.get("result").get("accessToken").asText();
            // when
            mockMvc.perform(
                    get("/articles/1")
                            .header("Authorization", "Bearer " + accessToken)
                            .accept(MediaType.APPLICATION_JSON)
            )
            // then
                    .andExpect(status().isOk());
        }
    }
}