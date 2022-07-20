package com.example.fashionblog.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
public class ErrorResponse {
    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime date = LocalDateTime.now();
//    private String debugMessage;
}
