package com.appointment.app.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
public class ApiError {

    private int code;
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ApiError(int code, HttpStatus status, String message, List<String> errors) {
        super();
        this.code = code;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(int code, HttpStatus status, String message, String error) {
        super();
        this.code = code;
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
}
