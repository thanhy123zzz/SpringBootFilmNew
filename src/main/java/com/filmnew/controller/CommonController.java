package com.filmnew.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.filmnew.Enity.film;
import com.filmnew.Enity.results;
import com.filmnew.Service.UserCommentService;
import com.filmnew.Service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class CommonController{
	ModelAndView mv = new ModelAndView();
	@Autowired
	UserService userService;
	@Autowired
	UserCommentService userCMTService;
	String urlPosterNothing = "https://image.tmdb.org/t/p/w500/cR6rQDFpMOQp2LzB6vL4ZcaDo1j.jpg";
	private final String urlImage = "https://image.tmdb.org/t/p/original";
	private final String urlBigImage = "https://image.tmdb.org/t/p/w500";
	private String urlFindWithKeyword(String query) {
		return "https://api.themoviedb.org/3/search/multi?api_key=b7c3309b3ea8fdf5c9afa62154eefc7f&language=en-US&page=1&include_adult=false&query="+query;
	}
	private final String urlTrending = "https://api.themoviedb.org/3/trending/all/day?api_key=b7c3309b3ea8fdf5c9afa62154eefc7f";
	private final String urlTopRated = "https://api.themoviedb.org/3/movie/top_rated?api_key=b7c3309b3ea8fdf5c9afa62154eefc7f&language=en-US&page=1";
	private String urlPopularTV(String page)
	{
	return "https://api.themoviedb.org/3/tv/popular?api_key=b7c3309b3ea8fdf5c9afa62154eefc7f&language=en-US&page="+page;
	}
	private final String urlLaste = "https://api.themoviedb.org/3/movie/latest?api_key=b7c3309b3ea8fdf5c9afa62154eefc7f&language=en-US";
	
	private String urlDetailFilm(String idFilm) {
		return "https://api.themoviedb.org/3/movie/"+idFilm+"?api_key=b7c3309b3ea8fdf5c9afa62154eefc7f&append_to_response=videos";
	}
	
	private String urlDetailTV(String idFilm) {
		return "https://api.themoviedb.org/3/tv/"+idFilm+"?api_key=b7c3309b3ea8fdf5c9afa62154eefc7f&language=en-US&append_to_response=videos";
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
	private String urlVideoTV(String id) {
		return "https://api.themoviedb.org/3/tv/"+id+"/videos?api_key=b7c3309b3ea8fdf5c9afa62154eefc7f&language=en-US";
	}
	
	private String getUrlCasts(String id) {
		return "http://api.themoviedb.org/3/movie/"+id+"/casts?api_key=b7c3309b3ea8fdf5c9afa62154eefc7f";
	}
	private String getUrlCastsTV(String id) {
		return "https://api.themoviedb.org/3/tv/"+id+"/credits?api_key=b7c3309b3ea8fdf5c9afa62154eefc7f&language=en-US";
	}
	private String getUrlReview(String id) {
		return "https://api.themoviedb.org/3/movie/"+id+"/reviews?api_key=b7c3309b3ea8fdf5c9afa62154eefc7f&language=en-US&page=1";
	}
	private String getUrlReviewTV(String id) {
		return "https://api.themoviedb.org/3/tv/"+id+"/reviews?api_key=b7c3309b3ea8fdf5c9afa62154eefc7f&language=en-US&page=1";
	}
	private String getUrlSimilarMovie(String id, String media_type)
	{
		return "https://api.themoviedb.org/3/"+media_type+"/"+id+"/similar?api_key=b7c3309b3ea8fdf5c9afa62154eefc7f&language=en-US&page=1";
	}
	public String findWithKeyword(String keyword) throws URISyntaxException, IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(urlFindWithKeyword(keyword)))
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
	public String getVideosTV(String idFilm) throws URISyntaxException, IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(urlVideoTV(idFilm)))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
	public String getCasts(String idFilm) throws URISyntaxException, IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(getUrlCasts(idFilm)))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
	public String getCastsTV(String idFilm) throws URISyntaxException, IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(getUrlCastsTV(idFilm)))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
	public String getReviews(String idFilm) throws URISyntaxException, IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(getUrlReview(idFilm)))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
	public String getReviewsTV(String idFilm) throws URISyntaxException, IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(getUrlReviewTV(idFilm)))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
	public String getTrending() throws URISyntaxException, IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(urlTrending))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
	public String getTopRated() throws URISyntaxException, IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(urlTopRated))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
	public String getPopularTV(String page) throws URISyntaxException, IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(urlPopularTV(page)))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
	public String getDetailTV(String idFilm) throws URISyntaxException, IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(urlDetailTV(idFilm)))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
	public String getSimilarMovie(String idFilm) throws URISyntaxException, IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(getUrlSimilarMovie(idFilm,"movie")))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}public String getSimilarTV(String idFilm) throws URISyntaxException, IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(getUrlSimilarMovie(idFilm,"tv")))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
	public String getNewFilm() throws URISyntaxException, IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(urlLaste))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
}
