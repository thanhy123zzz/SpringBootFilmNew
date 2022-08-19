package com.filmnew.Enity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class results {
private String original_title;
private String id;
private String name;
private String key;
private String title;
private String type;
private String imdb_id;
private String author;
private String content;
private author_details author_details;
private String original_name;
private String media_type;
private String backdrop_path;
private String poster_path;
private String release_date;
public String getRelease_date() {
	return release_date;
}
public void setRelease_date(String release_date) {
	this.release_date = release_date;
}
@DateTimeFormat(pattern = "MM/dd/yyyy")
private Date updated_at;
public String getMedia_type() {
	return media_type;
}
public void setMedia_type(String media_type) {
	this.media_type = media_type;
}
public String getOriginal_name() {
	return original_name;
}
public void setOriginal_name(String original_name) {
	this.original_name = original_name;
}
public String getAuthor() {
	return author;
}
public author_details getAuthor_details() {
	return author_details;
}
public void setAuthor_details(author_details author_details) {
	this.author_details = author_details;
}
public void setAuthor(String author) {
	this.author = author;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Date getUpdated_at() {
	return updated_at;
}
public void setUpdated_at(Date updated_at) {
	this.updated_at = updated_at;
}

public String getImdb_id() {
	return imdb_id;
}
public void setImdb_id(String imdb_id) {
	this.imdb_id = imdb_id;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getKey() {
	return key;
}
public void setKey(String key) {
	this.key = key;
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

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
@Override
public String toString() {
	return "results [original_title=" + original_title + ", id=" + id + ", name=" + name + ", key=" + key + ", title="
			+ title + ", type=" + type + ", backdrop_path=" + backdrop_path + ", poster_path=" + poster_path + "]";
}

}
