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

import com.filmnew.Enity.Cast;
import com.filmnew.Enity.Casts;
import com.filmnew.Enity.DetailFilm;
import com.filmnew.Enity.IMDB;
import com.filmnew.Enity.User;
import com.filmnew.Enity.comments;
import com.filmnew.Enity.film;
import com.filmnew.Enity.image;
import com.filmnew.Enity.results;
import com.filmnew.Enity.videos;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@RestController
public class HelloController extends CommonController {
	@RequestMapping(value = "/nhan", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public ModelAndView insert(@RequestBody User user) {
		int kq = userService.add(user);
		mv.setViewName("info");
		mv.addObject("user", user);
		mv.addObject("kq", kq);
		return mv;
	}
	@GetMapping("/")
	public ModelAndView hele(HttpServletRequest request)
			throws JsonSyntaxException, URISyntaxException, IOException, InterruptedException {
		film film = new Gson().fromJson(getDetailwPage("1"), film.class);
		film trending = new Gson().fromJson(getTrending(), film.class);
		film toprates = new Gson().fromJson(getTopRated(), film.class);
		List<results> slides = new ArrayList<results>();
		for(int i = 0;i<5;i++) {
			slides.add(film.getResults().get(i));
		}
		List<results> r = new ArrayList<results>();
		List<results> trend = new ArrayList<results>();
		List<results> toprate = new ArrayList<results>();
		for(int i = 0;i<8;i++) {
			r.add(film.getResults().get(i));
			trend.add(trending.getResults().get(i));
			toprate.add(toprates.getResults().get(i));
		}
		mv.addObject("slides", slides);
		mv.addObject("newMovies", r);
		mv.addObject("trends", trend);
		mv.addObject("toprate", toprate);
		mv.setViewName("index");
		return mv;
	}

//	@DeleteMapping(value="/xoa")
//	public String xoa(@RequestParam("name") String name, Model model) {
//		userService.delete(name);
//		throw new UserNotFoundException("ad");
//	}
	@PutMapping(value = "/sua")
	public String update(@RequestBody User user, Model model) {
		int kq = userService.update(user);
		model.addAttribute("kq", kq);
		return "info";
	}

	@GetMapping("/tim-kiem")
	public ModelAndView findFilm(@RequestParam(value = "keyword", required = false) String keyword)
			throws JsonSyntaxException, URISyntaxException, IOException, InterruptedException {
		List<image> images = new ArrayList<image>();
		List<String> urls = new ArrayList<String>();
		film film = new Gson().fromJson(findWithKeyword(keyword), film.class);
		for (results a : film.getResults()) {
			images.add(new Gson().fromJson(getDetailFilm(a.getId()), image.class));
		}
		for (image a : images) {
			if (a.getBackdrop_path() == null) {
				urls.add(urlPosterNothing);
			} else {
				urls.add(getURLImage(a.getBackdrop_path()));
			}
		}
		mv.addObject("urls", urls);
		mv.setViewName("info");
		return mv;
	}

	@GetMapping("/popular/{page}")
	public ModelAndView popular(@PathVariable("page") String page)
			throws JsonSyntaxException, URISyntaxException, IOException, InterruptedException {
		film film = new Gson().fromJson(getDetailwPage(page), film.class);

		List<results> r = film.getResults();

		for (int i = 0; i < r.size(); i++) {
			videos v = new Gson().fromJson(getVideos(r.get(i).getId()), videos.class);
			IMDB imdb = new Gson().fromJson(getDetailFilm(r.get(i).getId()), IMDB.class);
			String key = null;
			for (results a : v.getResults()) {
				if (a.getType().equals("Trailer")) {
					key = a.getKey();
					break;
				}
			}
			r.get(i).setImdb_id(imdb.getImdb_id());
			r.get(i).setKey(key);
		}

		mv.addObject("details", r);
		mv.setViewName("popular");
		return mv;
	}

	@GetMapping("/detail/{id}")
	public ModelAndView detailFilm(@PathVariable("id") String id)
			throws JsonSyntaxException, URISyntaxException, IOException, InterruptedException {
		mv.setViewName("detailFilm");
		DetailFilm dt = new Gson().fromJson(getDetailFilm(id), DetailFilm.class);
		videos v = new Gson().fromJson(getVideos(id), videos.class);
		Casts cs = new Gson().fromJson(getCasts(id), Casts.class);
		comments cms = new Gson().fromJson(getReviews(id), comments.class);
		String key = null;
		for(results r: v.getResults()) {
			if(r.getType().equals("Trailer")) {
				key = r.getKey();
				break;
			}
		}
		List<Cast> casts = new ArrayList<Cast>();
		for(int i = 0; i < cs.getCast().size();i++) {
			if(i==16) {
				break;
			}
			casts.add(cs.getCast().get(i));
		}
		List<results> r = new ArrayList<results>();
		for(int i = 0; i < cms.getResults().size();i++) {
			if(i==5)break;
			r.add(cms.getResults().get(i));
			if(r.get(i).getAuthor_details().getAvatar_path()==null || !r.get(i).getAuthor_details().getAvatar_path().startsWith("/http")) {
				r.get(i).getAuthor_details().setAvatar_path(null);
			}
			else {
				r.get(i).getAuthor_details().setAvatar_path(r.get(i).getAuthor_details().getAvatar_path().substring(1));
			}
		}
		mv.addObject("comments", r);
		mv.addObject("casts", casts);
		mv.addObject("key", key);
		mv.addObject("movie", dt);
		return mv;
	}
	@GetMapping("/watch/{id}")
	public ModelAndView watchMovie(@PathVariable("id") String id) throws JsonSyntaxException, URISyntaxException, IOException, InterruptedException {
		mv.setViewName("Watching");
		DetailFilm dt = new Gson().fromJson(getDetailFilm(id), DetailFilm.class);
		mv.addObject("movie", dt);
		return mv;
	}
	@GetMapping("/search")
	public ModelAndView searchMovie(@RequestParam("keyword")String keyWord) throws JsonSyntaxException, URISyntaxException, IOException, InterruptedException {
		mv.setViewName("Search");
		film film = new Gson().fromJson(findWithKeyword(keyWord), film.class);
		List<results> r = film.getResults();
		mv.addObject("SearchMovies", r);
		return mv;
	}
}
