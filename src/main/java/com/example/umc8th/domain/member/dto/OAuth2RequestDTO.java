package com.example.umc8th.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class OAuth2RequestDTO {

    @Getter
    @Builder
    public static class OAuth2TokenDTO {
        @JsonProperty("grant_type")
        private String grantType;

        @JsonProperty("client_id")
        private String clientId;

        @JsonProperty("redirect_uri")
        private String redirectUri;
        private String code;

        public MultiValueMap<String, String> toMap() {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("grant_type", "authorization_code");
            map.add("client_id", this.clientId);
            map.add("redirect_uri", this.redirectUri);
            map.add("code", this.code);
            return map;
        }
    }
}
