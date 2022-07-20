package com.example.fashionblog.exceptions;


import com.example.fashionblog.entity.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(AdminExistException.class)
    public ResponseEntity<ErrorResponse> handlerForAdminExistException(final AdminExistException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
//        errorResponse.setDebugMessage("Check if Someone has already signup with that email!");
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(errorResponse);
    }

    @ExceptionHandler(AdminNotFound.class)
    public ResponseEntity<ErrorResponse>handlerForAdminNotFoundException(final AdminNotFound ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
//        errorResponse.setDebugMessage("Check your login details , something is wrong ");
        errorResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(errorResponse);
    }


    @ExceptionHandler(CategoryExistException.class)
    public ResponseEntity<ErrorResponse>handlerForAdminNotFoundException(final CategoryExistException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
//        errorResponse.setDebugMessage("Already have this category!!");
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(errorResponse);
    }

    @ExceptionHandler(BlogNotExist.class)
    public ResponseEntity<ErrorResponse>handlerForAdminNotFoundException(final BlogNotExist ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
//        errorResponse.setDebugMessage("Verify if truly you have the blog");
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse>handlerForNoArgumentException(final HttpMessageNotReadableException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage().substring(0,33));
//        errorResponse.setDebugMessage("Verify if truly you have the blog");
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(errorResponse);
    }



    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse>handlerForCustomerException(final CustomerNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
//        errorResponse.setDebugMessage("Verify if truly you have the blog");
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(errorResponse);
    }

}
