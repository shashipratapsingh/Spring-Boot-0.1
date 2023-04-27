package com.HotelService.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FeedNotFound extends RuntimeException{
    private final String message;

    public FeedNotFound(String message) {
        super(message);
        this.message = message;
    }
}
