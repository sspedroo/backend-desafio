package com.desafiobackend.picpay.controller;

import com.desafiobackend.picpay.exceptions.PicPayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(PicPayException.class)
    public ProblemDetail handlePicPayException(PicPayException e) {
        return e.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e){

        List<InvalidParam> invalidParams = e.getFieldErrors()
                .stream()
                .map(fe -> new InvalidParam(fe.getField(), fe.getDefaultMessage()))
                .toList();

        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pb.setTitle("Your request parameters didn't validate");
        pb.setProperty("invalid-params", invalidParams);

        return pb;
    }

    private record InvalidParam(String fieldName, String message){}
}
