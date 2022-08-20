package com.filmnew.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController extends CommonController{
@ExceptionHandler(Exception.class)
public ModelAndView nullPointer(Exception e) {
	mv.setViewName("error");
	mv.addObject("status", e.getMessage());
	return mv;
	}
}
