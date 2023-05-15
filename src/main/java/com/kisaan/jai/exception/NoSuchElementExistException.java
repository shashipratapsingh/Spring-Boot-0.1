package com.kisaan.jai.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoSuchElementExistException extends RuntimeException{

	private static final long serialVersionUID = -9173590943351455284L;

	private String message;
}