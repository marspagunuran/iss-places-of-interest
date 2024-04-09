package org.mars.issplacesofinterest.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ISSLocationException.class)
    public ResponseEntity<ErrorResponse> handleISSLocationException(ISSLocationException exception, WebRequest webRequest) {
              return buildErrorResponse(exception, webRequest, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PlacesOfInterestException.class)
    public ResponseEntity<ErrorResponse> handlePlacesOfInterestException(PlacesOfInterestException exception,
                                                                  WebRequest webRequest){
        return buildErrorResponse(exception, webRequest, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException exception,
                                                                         WebRequest webRequest){
        return buildErrorResponse(exception, webRequest, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception,
                                                                         WebRequest webRequest){
        return buildErrorResponse(exception, webRequest, HttpStatus.BAD_REQUEST);
    }



    private ResponseEntity<ErrorResponse> buildErrorResponse(Throwable exception,
                                                             WebRequest webRequest,
                                                             HttpStatus status) {
        ErrorResponse errorDetails = new ErrorResponse(new Date().toString(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, status);
    }

}