package com.HotelService.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FeedNotUpdated extends RuntimeException{
     private final String massage;

    public FeedNotUpdated(String massage) {
        super(massage);
        this.massage = massage;
    }
}
