package com.filmnew.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.filmnew.Enity.User;
import com.filmnew.Service.UserService;

@RestController
public class ManagerController {

	@Autowired
	private UserService userService;

	private ModelAndView mav = new ModelAndView();

	// Login and SignUp

	@PostMapping("/login")
	public ModelAndView login(@ModelAttribute(name = "user") User user, HttpSession session) {
		List<User> list = userService.login(user.getName(), user.getPass());
		session.setAttribute("user", user);
		session.setMaxInactiveInterval(60 * 60 * 24);
		if (list.size() > 0) {
			User user1 = userService.finpOne(user.getName());
			if (user1.getIdfunction() == 1) {
				mav.setViewName("manager-user");
				return mav;
			} else {
				mav.setViewName("index");
				return mav;
			}
		} else {
			mav.addObject("messUser", "Username Or Pass Not Exist!");
			mav.setViewName("index");
			return mav;
		}
	}

	// Manager

	@GetMapping("/manager-user")
	public ModelAndView loadInforUser() {
		List<User> list = userService.findAll();
		mav.addObject("listUser", list);
		mav.addObject("num", 0);
		mav.setViewName("manager-user");
		mav.addObject("keyword", "");
		return mav;
	}

	@PostMapping({ "/manager-user/addUser" })
	public ModelAndView createUser(User user) {
		try {
			if (userService.finpOne(user.getName()).equals(null)) {
				userService.add(user);
			} else {
				throw new DuplicateKeyException("Tài khoản đã tồn tại");

			}
		} catch (IndexOutOfBoundsException exception) {
			userService.add(user);
			mav.setViewName("manager-user");
		}
		return mav;
	}

	@PostMapping("/manager-user/editUser")
	public ModelAndView updateUser(User user) {
		userService.update(user);
		return mav;
	}

	@GetMapping({ "/manager-user/findOne" })
	public User findUser(String name) {
		User user = userService.finpOne(name);
		return user;
	}

	@GetMapping({ "/manager-user/search" })
	public ModelAndView searchUser(@RequestParam("keyword") String search) {
		if (search == null) {
			List<User> list = userService.findAll();
			mav.addObject("listUser", list);
			mav.addObject("keyword", search);
			return mav;

		} else {
			List<User> list = userService.findlistbyname(search);
			mav.addObject("listUser", list);
			mav.addObject("keyword", search);
			return mav;
		}
	}

	@GetMapping("/manager-user/removeUser")
	public ModelAndView deleteUser(String name) {
		userService.delete(name);
		return mav;
	}

	@GetMapping("/manager-user/delete")
	public ModelAndView delete(String name) {
		userService.delete(name);
		return mav;
	}

	@GetMapping("/manager-film")
	public ModelAndView loadInforFilm() {
		mav.setViewName("manager-film");
		return mav;
	}

	@GetMapping("/manager-comment")
	public ModelAndView loadInforComment() {
		mav.setViewName("manager-comment");
		return mav;
	}

	@GetMapping("/manager-favorite")
	public ModelAndView loadInforFavorite() {
		mav.setViewName("manager-favorite");
		return mav;
	}

}
