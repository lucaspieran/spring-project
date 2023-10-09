package com.lucas.lucas.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponseUtil {
    public static ResponseEntity<Object> handleErrorResponse(String errorMessage, HttpStatus httpStatus) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", errorMessage);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
