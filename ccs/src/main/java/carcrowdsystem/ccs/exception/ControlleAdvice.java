package carcrowdsystem.ccs.exception;

import carcrowdsystem.ccs.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControlleAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(MyException ex, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse(
                ex.httpCode,
                ex.message,
                ex.errorCode,
                null
        );
        return new ResponseEntity(errorResponse, HttpStatus.valueOf(ex.httpCode));
    }
}
