package com.kisaan.jai.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ElementAlreadyExistException extends RuntimeException {

    private String message;
}
