package com.filmnew.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.filmnew.Enity.IMDB;
import com.filmnew.Enity.User;
import com.filmnew.Enity.film;
import com.filmnew.Enity.image;
import com.filmnew.Enity.results;
import com.filmnew.Enity.videos;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
@RestController
public class HelloController extends CommonController{
	@RequestMapping(value="/nhan",method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public ModelAndView insert(@RequestBody User user) {
		int kq = userService.add(user);
		mv.setViewName("info");
		mv.addObject("user", user);
		mv.addObject("kq", kq);
		return mv;
	}
	@GetMapping("/")
	public ModelAndView hele(HttpServletRequest request) throws JsonSyntaxException, URISyntaxException, IOException, InterruptedException {
		mv.setViewName("index");
		mv.addObject("msg", "chào mọi người!");
		return mv;
	}
//	@DeleteMapping(value="/xoa")
//	public String xoa(@RequestParam("name") String name, Model model) {
//		userService.delete(name);
//		throw new UserNotFoundException("ad");
//	}
	@PutMapping(value="/sua")
	public String update(@RequestBody User user, Model model) {
		int kq = userService.update(user);
		model.addAttribute("kq", kq);
		return "info";
	}
	@GetMapping("/tim-kiem")
	public ModelAndView findFilm(@RequestParam(value = "keyword",required = false) String keyword) throws JsonSyntaxException, URISyntaxException, IOException, InterruptedException {
		List<image> images = new ArrayList<image>();
		List<String> urls = new ArrayList<String>();
		film film = new Gson().fromJson(findWithKeyword(keyword), film.class);
		for(results a : film.getResults())
		{
			images.add(new Gson().fromJson(getDetailFilm(a.getId()), image.class));
		}
		for(image a : images) {
			if(a.getBackdrop_path() == null) {
				urls.add(urlPosterNothing);
			}
			else {
				urls.add(getURLImage(a.getBackdrop_path()));
			}
		}
		mv.addObject("urls", urls);
		mv.setViewName("info");
		return mv;
	}
	@GetMapping("/popular/{page}")
	public ModelAndView popular(@PathVariable("page") String page) throws JsonSyntaxException, URISyntaxException, IOException, InterruptedException {
		film film = new Gson().fromJson(getDetailwPage(page), film.class);
		
		List<results> r = film.getResults();
		
		for(int i = 0; i < r.size(); i++) {
			videos v = new Gson().fromJson(getVideos(r.get(i).getId()), videos.class);
			IMDB imdb = new Gson().fromJson(getDetailFilm(r.get(i).getId()), IMDB.class);
			String key = null;
			for(results a : v.getResults())
			{
				if(a.getType().equals("Trailer")) {
					key = a.getKey();
					break;
				}
			}
			r.get(i).setImdb_id(imdb.getImdb_id());
			r.get(i).setKey(key);
		}
		
		mv.addObject("details",r);
		mv.setViewName("popular");
		return mv;
	}
}
