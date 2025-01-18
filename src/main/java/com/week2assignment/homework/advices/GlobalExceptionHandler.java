package com.week2assignment.homework.advices;


import com.week2assignment.homework.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundHandler(ResourceNotFoundException exception){
        ApiError apiError=ApiError
                .builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        return buildErrorApiResponse(apiError);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> internalServerErrorHandler(Exception exception){
        ApiError apiError=ApiError
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return buildErrorApiResponse(apiError);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse>handleInputValidationErrors(MethodArgumentNotValidException exception){
        List<String>errors=exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error->error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError apiError=ApiError
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Input validations failed")
                .subErrors(errors)
                .build();
        return buildErrorApiResponse(apiError);
    }

    private ResponseEntity<ApiResponse> buildErrorApiResponse(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getStatus());
    }
}
