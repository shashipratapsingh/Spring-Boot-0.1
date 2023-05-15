package com.kisaan.jai.exception;

import com.kisaan.jai.payload.response.ApiResponse;
import com.kisaan.jai.payload.response.ResponseHandler;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NoSuchElementExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ResponseEntity<Object> NoElementExistException(NoSuchElementExistException ex) {
        return ResponseHandler.prepareResponse(
                ApiResponse.builder().message(ex.getMessage()).httpStatus(HttpStatus.NOT_FOUND).build());
    }

    @ExceptionHandler(value = ElementAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<Object> ElementAlreadyExistException(ElementAlreadyExistException ex) {
        return ResponseHandler.prepareResponse(
                ApiResponse.builder().message(ex.getMessage()).httpStatus(HttpStatus.BAD_REQUEST).build());
    }

    @ExceptionHandler(value = InvalidOTPException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<Object> InvalidOTpException(InvalidOTPException ex) {
        return ResponseHandler.prepareResponse(
                ApiResponse.builder().farmerId(ex.getFarmerId())
                        .isRegistered(ex.getRegistered())
                        .message(ex.getMessage())
                        .httpStatus(HttpStatus.BAD_REQUEST).build());
    }

    @ExceptionHandler(value = MobileNumNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<Object> MobileNumberNotFoundException(MobileNumNotFoundException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        if("OTP Verified".equals(ex.getMessage())) {
            status = HttpStatus.OK;
        }

        return ResponseHandler.prepareResponse(
                ApiResponse.builder().farmerId(ex.getFarmerId())
                        .isRegistered(ex.getRegistered())
                        .message(ex.getMessage())
                        .httpStatus(status).build());
    }


    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> resp=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName=((FieldError) error).getField();
            String message=error.getDefaultMessage();
            resp.put(fieldName, message);
        });
        return ResponseHandler.prepareResponse(
                ApiResponse.builder().message("Error").httpStatus(HttpStatus.BAD_REQUEST).responseBody(resp).build());
    }

    @ExceptionHandler(value = {ConstraintViolationException.class, MethodArgumentTypeMismatchException.class,
            DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> validationExceptionHandler(Exception ex) {
        Map<String, String> resp = new HashMap<>();

        if (ex instanceof ConstraintViolationException) {
            ConstraintViolationException exception = ((ConstraintViolationException) ex);
            exception.getConstraintViolations().stream().forEach((error) -> {
                String message = error.getMessage();
                resp.put("message", message);
            });
        } else if (ex instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException exception = ((MethodArgumentTypeMismatchException) ex);
            exception.getMessage();
            String message = new StringBuilder("Invalid ")
                    .append(exception.getName())
                    .append(" (").append(exception.getValue()).append(")").toString();

            resp.put("message", message);
        } else if (ex instanceof DataIntegrityViolationException) {
            DataIntegrityViolationException exception = ((DataIntegrityViolationException) ex);

            resp.put("message", exception.getCause().getCause().getMessage());
        }
        return ResponseHandler.prepareResponse(
                ApiResponse.builder().message("Error").httpStatus(HttpStatus.BAD_REQUEST).responseBody(resp).build());
    }


    @ExceptionHandler(value = NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ResponseEntity<Object> NoElementException(NoSuchElementException ex) {
        return ResponseHandler.prepareResponse(
                ApiResponse.builder().message(ex.getMessage()).httpStatus(HttpStatus.NOT_FOUND).build()
        );
    }

    @ExceptionHandler(value = InvalidDateRangeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<Object> InvalidDateRangeException(InvalidDateRangeException ex) {
        return ResponseHandler.prepareResponse(
                ApiResponse.builder().message(ex.getMessage()).httpStatus(HttpStatus.BAD_REQUEST).build());
    }
}
