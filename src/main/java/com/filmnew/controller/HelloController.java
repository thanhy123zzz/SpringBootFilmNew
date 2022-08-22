package com.filmnew.controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.filmnew.Enity.Counter;
import com.filmnew.Enity.DetailFilm;
import com.filmnew.Enity.IMDB;
import com.filmnew.Enity.User;
import com.filmnew.Enity.UserComments;
import com.filmnew.Enity.comments;
import com.filmnew.Enity.film;
import com.filmnew.Enity.image;
import com.filmnew.Enity.results;
import com.filmnew.Enity.sesion;
import com.filmnew.Enity.videos;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@RestController
public class HelloController extends CommonController {
	@GetMapping("/")
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
		mv.addObject("tvs", poupularTV);
		mv.addObject("slides", slides);
		mv.addObject("newMovies", r);
		mv.addObject("trends", trend);
		mv.addObject("toprate", toprate);
		mv.addObject("index", 1);
		mv.setViewName("index");
		return mv;
	}

	// @DeleteMapping(value="/xoa")
	// public String xoa(@RequestParam("name") String name, Model model) {
	// userService.delete(name);
	// throw new UserNotFoundException("ad");
	// }

	@GetMapping("/detail/{id}")
	public ModelAndView detailFilm(@PathVariable("id") String id)
			throws JsonSyntaxException, URISyntaxException, IOException, InterruptedException {
		mv.setViewName("detailFilm");
		DetailFilm dt = new Gson().fromJson(getDetailFilm(id), DetailFilm.class);
		videos v = new Gson().fromJson(getVideos(id), videos.class);
		Casts cs = new Gson().fromJson(getCasts(id), Casts.class);
		comments cms = new Gson().fromJson(getReviews(id), comments.class);
		film similar = new Gson().fromJson(getSimilarMovie(id), film.class);
		String key = null;
		for (results r : v.getResults()) {
			if (r.getType().equals("Trailer")) {
				key = r.getKey();
				break;
			}
		}
		List<Cast> casts = new ArrayList<Cast>();
		for (int i = 0; i < cs.getCast().size(); i++) {
			if (i == 16) {
				break;
			}
			casts.add(cs.getCast().get(i));
		}
		List<results> r = new ArrayList<results>();
		for (int i = 0; i < cms.getResults().size(); i++) {
			if (i == 5)
				break;
			r.add(cms.getResults().get(i));
			if (r.get(i).getAuthor_details().getAvatar_path() == null
					|| !r.get(i).getAuthor_details().getAvatar_path().startsWith("/http")) {
				r.get(i).getAuthor_details().setAvatar_path(null);
			} else {
				r.get(i).getAuthor_details().setAvatar_path(r.get(i).getAuthor_details().getAvatar_path().substring(1));
			}
		}
		List<results> slm = new ArrayList<results>();
		for (int i = 0; i < 4; i++) {
			slm.add(similar.getResults().get(i));
		}
		mv.addObject("slm", slm);
		mv.addObject("comments", r);
		mv.addObject("casts", casts);
		mv.addObject("key", key);
		mv.addObject("movie", dt);
		return mv;
	}

	@GetMapping("/watch/{id}")
	public ModelAndView watchMovie(@PathVariable("id") String id)
			throws JsonSyntaxException, URISyntaxException, IOException, InterruptedException {
		mv.setViewName("Watching");
		DetailFilm dt = new Gson().fromJson(getDetailFilm(id), DetailFilm.class);
		film similar = new Gson().fromJson(getSimilarMovie(id), film.class);
		List<UserComments> cmts = userCMTService.getComment(id);
		List<results> slm = new ArrayList<results>();
		for (int i = 0; i < 4; i++) {
			slm.add(similar.getResults().get(i));
		}
		mv.addObject("cmts", cmts);
		mv.addObject("slm", slm);
		mv.addObject("movie", dt);
		return mv;
	}

	@GetMapping("/search")
	public ModelAndView searchMovie(@RequestParam("keyword") String keyWord)
			throws JsonSyntaxException, URISyntaxException, IOException, InterruptedException {
		mv.setViewName("Search");
		film film = new Gson().fromJson(findWithKeyword(keyWord.replaceAll(" ", "+")), film.class);
		List<results> r = film.getResults();
		mv.addObject("SearchMovies", r);
		mv.addObject("key", keyWord);
		return mv;
	}

	@GetMapping("/detail/tv/{id}")
	public ModelAndView detailTV(@PathVariable("id") String id)
			throws JsonSyntaxException, URISyntaxException, IOException, InterruptedException {
		videos v = new Gson().fromJson(getVideosTV(id), videos.class);
		DetailFilm dt = new Gson().fromJson(getDetailTV(id), DetailFilm.class);
		film similar = new Gson().fromJson(getSimilarTV(id), film.class);
		String key = null;
		for (results r : v.getResults()) {
			if (r.getType().equals("Trailer")) {
				key = r.getKey();
				break;
			}
		}
		Casts cs = new Gson().fromJson(getCastsTV(id), Casts.class);
		List<Cast> casts = new ArrayList<Cast>();
		for (int i = 0; i < cs.getCast().size(); i++) {
			if (i == 16) {
				break;
			}
			casts.add(cs.getCast().get(i));
		}
		comments cms = new Gson().fromJson(getReviewsTV(id), comments.class);
		List<results> r = new ArrayList<results>();
		for (int i = 0; i < cms.getResults().size(); i++) {
			if (i == 5)
				break;
			r.add(cms.getResults().get(i));
			if (r.get(i).getAuthor_details().getAvatar_path() == null
					|| !r.get(i).getAuthor_details().getAvatar_path().startsWith("/http")) {
				r.get(i).getAuthor_details().setAvatar_path(null);
			} else {
				r.get(i).getAuthor_details().setAvatar_path(r.get(i).getAuthor_details().getAvatar_path().substring(1));
			}
		}
		List<results> slm = new ArrayList<results>();
		for (int i = 0; i < 4; i++) {
			slm.add(similar.getResults().get(i));
		}
		mv.addObject("slm", slm);
		mv.addObject("comments", r);
		mv.addObject("key", key);
		mv.addObject("casts", casts);
		mv.setViewName("detailTV");
		mv.addObject("movie", dt);
		return mv;
	}

	@GetMapping("/watch/tv/{id}/{ss}/{ep}")
	public ModelAndView watchTV(@PathVariable("id") int id, @PathVariable("ss") int sesion, @PathVariable("ep") int ep)
			throws JsonSyntaxException, URISyntaxException, IOException, InterruptedException {
		DetailFilm dt = new Gson().fromJson(getDetailTV(String.valueOf(id)), DetailFilm.class);
		film similar = new Gson().fromJson(getSimilarTV(String.valueOf(id)), film.class);
		List<results> slm = new ArrayList<results>();
		for (int i = 0; i < 4; i++) {
			slm.add(similar.getResults().get(i));
		}
		mv.addObject("slm", slm);
		mv.addObject("movie", dt);
		mv.addObject("ep", ep);
		mv.addObject("ss", sesion);
		mv.setViewName("watchTV");

		return mv;
	}

	@GetMapping("/movies/{page}")
	public ModelAndView Movies(@PathVariable("page") String page)
			throws JsonSyntaxException, URISyntaxException, IOException, InterruptedException {
		film film = new Gson().fromJson(getDetailwPage(page), film.class);
		mv.addObject("movies", film.getResults());
		mv.addObject("index", 2);
		ArrayList<Integer> pages = new ArrayList<Integer>();
		pages.add(Integer.parseInt(page));
		pages.add(Integer.parseInt(page)-1);
		pages.add(Integer.parseInt(page)+1);
		mv.addObject("pages", pages);
		mv.setViewName("Movies");
		return mv;
	}

	@GetMapping("/tv/{page}")
	public ModelAndView Tv(@PathVariable("page") String page)
			throws JsonSyntaxException, URISyntaxException, IOException, InterruptedException {
		film film = new Gson().fromJson(getPopularTV(page), film.class);
		mv.addObject("tv", film.getResults());
		mv.addObject("index", 3);
		mv.addObject("pages", Integer.parseInt(page));
		mv.setViewName("TV");
		return mv;
	}
	
	@RequestMapping(value="/insert-comment/{id}", method=RequestMethod.POST)
	public ModelAndView getEventCount(@PathVariable("id") String id,@ModelAttribute("UserComments") UserComments cmt) throws JsonSyntaxException, URISyntaxException, IOException, InterruptedException { 
		if(cmt.getName().isEmpty()){
			cmt.setName("freeUser");
		}
		cmt.setId_film(id);
		userCMTService.insertComment(cmt);
		mv.setViewName("redirect:/watch/"+id);
	    return mv;
	}
}
