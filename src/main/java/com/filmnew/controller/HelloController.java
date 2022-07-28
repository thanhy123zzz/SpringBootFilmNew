package com.filmnew.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.filmnew.Enity.User;

@Controller
public class HelloController {
	@RequestMapping(value="/nhan",method = RequestMethod.POST)
	public String hello(@ModelAttribute User user, Model model) {
		model.addAttribute("user", user);
		return "hehehe";
	}
	@RequestMapping("/")
	public String hele(HttpServletRequest request) {
		request.setAttribute("msg", "chào mọi người!");
		return "hello";
	}
}
