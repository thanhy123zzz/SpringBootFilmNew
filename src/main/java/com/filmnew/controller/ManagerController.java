package com.filmnew.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.filmnew.Enity.User;
import com.filmnew.Enity.film;
import com.filmnew.Enity.results;
import com.filmnew.Service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@RestController
public class ManagerController extends CommonController {
	@Autowired
	private UserService userService;

	private ModelAndView mav = new ModelAndView();

	public ModelAndView hihi() throws JsonSyntaxException, URISyntaxException, IOException, InterruptedException {
		film film = new Gson().fromJson(getDetailwPage("1"), film.class);
		film trending = new Gson().fromJson(getTrending(), film.class);
		film toprates = new Gson().fromJson(getTopRated(), film.class);
		film popularTVs = new Gson().fromJson(getPopularTV("1"), film.class);

		List<results> slides = new ArrayList<results>();
		for (int i = 0; i < 5; i++) {
			slides.add(trending.getResults().get(i));
		}
		List<results> r = new ArrayList<results>();
		List<results> trend = new ArrayList<results>();
		List<results> toprate = new ArrayList<results>();
		List<results> poupularTV = new ArrayList<results>();
		for (int i = 0; i < 8; i++) {
			r.add(film.getResults().get(i));
			trend.add(trending.getResults().get(i));
			toprate.add(toprates.getResults().get(i));
			poupularTV.add(popularTVs.getResults().get(i));
		}
		mav.addObject("tvs", poupularTV);
		mav.addObject("slides", slides);
		mav.addObject("newMovies", r);
		mav.addObject("trends", trend);
		mav.addObject("toprate", toprate);
		return mav;
	}

	public ModelAndView hele(HttpServletRequest request)
			throws JsonSyntaxException, URISyntaxException, IOException, InterruptedException {
		film film = new Gson().fromJson(getDetailwPage("1"), film.class);
		film trending = new Gson().fromJson(getTrending(), film.class);
		film toprates = new Gson().fromJson(getTopRated(), film.class);
		film popularTVs = new Gson().fromJson(getPopularTV("1"), film.class);

		List<results> slides = new ArrayList<results>();
		for (int i = 0; i < 5; i++) {
			slides.add(trending.getResults().get(i));
		}
		List<results> r = new ArrayList<results>();
		List<results> trend = new ArrayList<results>();
		List<results> toprate = new ArrayList<results>();
		List<results> poupularTV = new ArrayList<results>();
		for (int i = 0; i < 8; i++) {
			r.add(film.getResults().get(i));
			trend.add(trending.getResults().get(i));
			toprate.add(toprates.getResults().get(i));
			poupularTV.add(popularTVs.getResults().get(i));
		}
		mav.addObject("tvs", poupularTV);
		mav.addObject("slides", slides);
		mav.addObject("newMovies", r);
		mav.addObject("trends", trend);
		mav.addObject("toprate", toprate);
		mav.setViewName("index");
		return mav;
	}

	// Login and SignUp
	@PostMapping("/login")
	public ModelAndView login(@ModelAttribute(name = "user") User user, HttpSession session, HttpServletRequest res)
			throws JsonSyntaxException, URISyntaxException, IOException, InterruptedException {
		User user1 = userService.login(user.getName(), user.getPass());
		if (user1.getName().equals(null) && user.equals(null)) {
			mav.addObject("messError", "Username Or PassWord Not Is True");
			return hele(res);
		} else {
			if (user1.getName().equals(user.getName()) && user1.getPass().equals(user.getPass())) {
				session.setAttribute("NAME_USER", user1.getName());
				session.setAttribute("ROLE_USER", user1.getIdfunction());
				session.setMaxInactiveInterval(60 * 60 * 24);
				if (user1.getIdfunction() == 1) {
					mav.setViewName("redirect:/manager-user");
					return mav;
				} else {
					mav.setViewName("redirect:/");
					return mav;
				}
			} else {
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
				mav.addObject("messError", "Username Or PassWord Not Is True");
				return hele(res);
			}
		}

	}

	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session, HttpServletRequest res)
			throws JsonSyntaxException, URISyntaxException, IOException, InterruptedException {
		session.removeAttribute("NAME_USER");
		session.removeAttribute("ROLE_USER");
		mav.setViewName("index");
		hele(res);
		return mav;
	}

	@PostMapping("/signup")
	public ModelAndView signup(@ModelAttribute(name = "user") User user, HttpServletRequest request)
			throws JsonSyntaxException, URISyntaxException, IOException, InterruptedException {
		User user1 = new User();
		try {
			user1 = userService.finpOne(user.getName());
			String pass = user.getPass();
			String repass = user.getRepass();
			if (user1.getName().equals(null) && user.getPass().equals(null)) {
				if (!user1.getName().equals("")) {
					mav.addObject("ErrorSign", "Username Was Exist");
					hele(request);
				} else {
					if (repass.equals(pass)) {
						User user3 = new User();
						user3.setName(user.getName());
						user3.setPass(user.getPass());
						userService.signup(user3); // function Insert User signup()
						mav.addObject("name", user.getName());
						mav.addObject("messError", "Sign Up Success");
						hele(request);
					} else {
						mav.addObject("ErrorSign", "Password and Respassword Is Not The Same");
						hele(request);
					}
				}

			} else {
				if (!user1.getName().equals("")) {
					mav.addObject("ErrorSign", "Username Was Exist");
					hele(request);
				} else {
					if (repass.equals(pass)) {
						User user3 = new User();
						user3.setName(user.getName());
						user3.setPass(user.getPass());
						userService.signup(user3);
						mav.addObject("name", user.getName());
						mav.addObject("messError", "Sign Up Success");
						hele(request);
					} else {
						mav.addObject("ErrorSign", "Password and Respassword Is Not The Same");
						hele(request);
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
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
		if (userService.finpOne(user.getName()).equals(null)) {
			userService.add(user);
		} else {
			mav.addObject("mess", "Tài khoản đã tồn tại");
			return mav;
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
