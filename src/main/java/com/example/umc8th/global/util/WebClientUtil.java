package com.example.umc8th.global.util;

import com.example.umc8th.global.exception.WebClientErrorCode;
import com.example.umc8th.global.exception.WebClientException;
import io.netty.channel.ChannelOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Slf4j
@Component
public class WebClientUtil {

    public WebClient getWebClient(String baseUrl) {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000); // 10s

        return WebClient.builder()
                .baseUrl(baseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .filter(loggingRequest())
                .filter(loggingResponse())
                .build();
    }

    private ExchangeFilterFunction loggingRequest() {
        return ((request, next) -> {
            log.info("WebClient Api Request -> method: {}, url: {}", request.method(), request.url());
            return next.exchange(request);
        });
    }

    private ExchangeFilterFunction loggingResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(response -> {
            if (response.statusCode().isError()) {
                response.bodyToMono(String.class).flatMap(error -> {
                    log.error("WebClient Api error: {}", error);
                    return Mono.error(new WebClientException(WebClientErrorCode.WEB_CLIENT_ERROR_CODE));
                });
            }
            return Mono.just(response);
        });
    }
}
