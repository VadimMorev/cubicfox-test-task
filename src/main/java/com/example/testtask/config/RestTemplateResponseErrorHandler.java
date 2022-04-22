package com.example.testtask.config;

import com.example.testtask.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(final ClientHttpResponse clientHttpResponse) throws IOException {
        return (clientHttpResponse
                .getStatusCode()
                .series() == HttpStatus.Series.CLIENT_ERROR || clientHttpResponse
                .getStatusCode()
                .series() == HttpStatus.Series.SERVER_ERROR);
    }

    @Override
    public void handleError(final ClientHttpResponse clientHttpResponse) throws IOException {
        if (clientHttpResponse
                .getStatusCode()
                .series() == HttpStatus.Series.SERVER_ERROR) {
            throw new HttpClientErrorException(clientHttpResponse.getStatusCode());
        } else if (clientHttpResponse
                .getStatusCode()
                .series() == HttpStatus.Series.CLIENT_ERROR) {
            if (clientHttpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NotFoundException();
            }
            throw new HttpServerErrorException(clientHttpResponse.getStatusCode());
        }
    }
}