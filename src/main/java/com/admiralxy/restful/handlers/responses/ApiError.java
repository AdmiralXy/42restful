package com.admiralxy.restful.handlers.responses;

import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
public class ApiError {

    @Value
    private static class Error {
        Integer status;
        String message;
    }

    Error error;

    public ApiError(HttpStatus status, String message) {
        this.error = new Error(status.value(), message);
    }

}
