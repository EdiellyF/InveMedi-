package com.br.InveMedi.inveMedi.exceptions;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Getter
@Setter
public class ErrorResponse {

    private final int status;
    private final String message;
    private String stackTrace;
    private List<ValidationError> validationErrors;

    @RequiredArgsConstructor
    @Getter
    @Setter
    private static class ValidationError {
        private final String field;
        private final String message;
    }


    public void addValidationError(String field, String message) {
        if(Objects.isNull(validationErrors)){
            this.validationErrors = new ArrayList<>();
        }
        this.validationErrors.add(new ValidationError(field, message));
    }


    public String toJson(){
        return "{\"status\": " + getStatus() + ", " +
                "\"message\": \"" + getMessage() + "\"}";
    }
}
