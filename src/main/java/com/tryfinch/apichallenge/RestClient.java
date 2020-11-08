package com.tryfinch.apichallenge;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestClient {

    private final RestTemplate restTemplate;

    // this logic retries all operational errors (network down, connection reset, connection refused, etc)
    @Retryable(maxAttemptsExpression = "#{${retry.maxAttempts}}", backoff = @Backoff(delayExpression = "#{${retry.maxDelay}}"))
    public <T> ResponseEntity<T> postForEntity(String url, @Nullable Object request,
                                               Class<T> responseType, Object... uriVariables) throws RestClientException {

        log.trace("getting an entity. url: {}, request: {}, responseType: {}, uriVariables: {}",
                url, request, responseType, uriVariables);

        ResponseEntity<T> response = restTemplate.postForEntity(url, request, responseType, uriVariables);

        if (response.getStatusCode().is5xxServerError() || response.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
            throw new RuntimeException("Got a " + response + " status from " + url + ". Retrying.");
        }

        return response;
    }


    @Recover
    public <T> ResponseEntity<T> recover(Exception e, String url, @Nullable Object request,
                 Class<?> responseType, Object... uriVariables) throws Exception {
        log.warn("failed getting an entity. url: {}, request: {}, responseType: {}, uriVariables: {}",
                url, request, responseType, uriVariables, e);
        throw new RestClientException("error getting rest resource", e);
    }
}
