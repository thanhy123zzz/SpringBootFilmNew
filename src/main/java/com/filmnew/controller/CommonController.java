package com.filmnew.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.filmnew.Service.UserService;

public class CommonController{
	ModelAndView mv = new ModelAndView();
	@Autowired
	UserService userService;
	String urlPosterNothing = "https://image.tmdb.org/t/p/w500/cR6rQDFpMOQp2LzB6vL4ZcaDo1j.jpg";
	private final String urlImage = "https://image.tmdb.org/t/p/original";
	private final String urlBigImage = "https://image.tmdb.org/t/p/w500";
	private final String urlFindWithKeyword = "https://api.themoviedb.org/3/search/keyword?api_key=b7c3309b3ea8fdf5c9afa62154eefc7f&page=1&query=";
	private String urlDetailFilm(String idFilm) {
		return "https://api.themoviedb.org/3/movie/"+idFilm+"?api_key=b7c3309b3ea8fdf5c9afa62154eefc7f&append_to_response=videos";
	}
	public String getURLImage(String url) {
		return urlBigImage+url;
	}
	private String getUrlPopular(String page) {
		return "https://api.themoviedb.org/3/movie/popular?api_key=b7c3309b3ea8fdf5c9afa62154eefc7f&language=en-US&page="+page;
	}
	private String getVideo(String id) {
		return "https://api.themoviedb.org/3/movie/"+id+"/videos?api_key=b7c3309b3ea8fdf5c9afa62154eefc7f&language=en-US";
	}
	public String findWithKeyword(String keyword) throws URISyntaxException, IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(urlFindWithKeyword + keyword))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
	public String getDetailFilm(String idFilm) throws URISyntaxException, IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(urlDetailFilm(idFilm)))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
	public String getDetailwPage(String page) throws URISyntaxException, IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(getUrlPopular(page)))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
	public String getVideos(String idFilm) throws URISyntaxException, IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(getVideo(idFilm)))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
}
