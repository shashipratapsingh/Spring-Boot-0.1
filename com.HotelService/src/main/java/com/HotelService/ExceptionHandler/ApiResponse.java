package com.HotelService.ExceptionHandler;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse extends RuntimeException{

    private final Object data;
    private final Boolean success;
    private final String timestamp;
    private final String cause;
    private final String path;

    public ApiResponse(Object data, Boolean success, String timestamp, String cause, String path) {
        this.data = data;
        this.success = success;
        this.timestamp = timestamp;
        this.cause = cause;
        this.path = path;
    }
}
