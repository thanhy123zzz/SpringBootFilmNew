package com.filmnew.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(NullPointerException.class)
 public String nullPointer(Exception ex) {
	ex.printStackTrace();
		return "error";
	}
}
