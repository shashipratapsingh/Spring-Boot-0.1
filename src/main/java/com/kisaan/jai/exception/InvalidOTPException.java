package com.kisaan.jai.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class InvalidOTPException extends RuntimeException{

    private String message;

    private String farmerId;

    private String registered;
}
