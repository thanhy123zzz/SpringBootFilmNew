package com.filmnew.Enity;

public class DetailFilm {
private String id;
private String imdb_id;
private String original_title;
private String backdrop_path;
private String poster_path;
private String release_date;
private String overview;
private double vote_average;
private int vote_count;
private String tagline;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getImdb_id() {
	return imdb_id;
}
public void setImdb_id(String imdb_id) {
	this.imdb_id = imdb_id;
}
public String getOriginal_title() {
	return original_title;
}
public void setOriginal_title(String original_title) {
	this.original_title = original_title;
}
public String getBackdrop_path() {
	return backdrop_path;
}
public void setBackdrop_path(String backdrop_path) {
	this.backdrop_path = backdrop_path;
}
public String getPoster_path() {
	return poster_path;
}
public void setPoster_path(String poster_path) {
	this.poster_path = poster_path;
}
public String getRelease_date() {
	return release_date;
}
public void setRelease_date(String release_date) {
	this.release_date = release_date;
}
public String getOverview() {
	return overview;
}
public void setOverview(String overview) {
	this.overview = overview;
}
public String getTagline() {
	return tagline;
}
public void setTagline(String tagline) {
	this.tagline = tagline;
}
public double getVote_average() {
	return vote_average;
}
public void setVote_average(double vote_average) {
	this.vote_average = vote_average;
}
public int getVote_count() {
	return vote_count;
}
public void setVote_count(int vote_count) {
	this.vote_count = vote_count;
}
}
