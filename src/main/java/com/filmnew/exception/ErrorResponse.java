package com.filmnew.exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
	private HttpStatus status;
	private String message;
	public ErrorResponse() {
		
	}
	
    public ErrorResponse(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	
    public void setStatus(HttpStatus status) {
    	this.status = status;
    }
    
    public HttpStatus getStatus() {
    	return this.status;
    }
    
    public void setMessage(String message) {
    	this.message = message;
    }
    
    public String getMessage() {
    	return this.message;
    }
}
