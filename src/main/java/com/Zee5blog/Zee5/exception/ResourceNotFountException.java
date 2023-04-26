package com.Zee5blog.Zee5.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFountException extends RuntimeException{

	String resourceName;
	String fieldName;
	long fieldValue;
	
	public ResourceNotFountException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s Not found with this name %s : %s ", resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public ResourceNotFountException(String string, String string2) {
		// TODO Auto-generated constructor stub
	}
	
	
}
