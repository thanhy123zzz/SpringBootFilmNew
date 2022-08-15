package com.filmnew.Enity;

public class IMDB {
private String imdb_id;
private String id;
public String getImdb_id() {
	return imdb_id;
}
public void setImdb_id(String imdb_id) {
	this.imdb_id = imdb_id;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
@Override
public String toString() {
	return "DetailFilm [imdb_id=" + imdb_id + ", id=" + id + "]";
}
}
