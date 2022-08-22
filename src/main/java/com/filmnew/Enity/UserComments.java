package com.filmnew.Enity;

import java.util.Date;

public class UserComments {
private String name;
private String id_film;
private String content_comment;
private Date time_comment;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Date getTime_comment() {
	return time_comment;
}
public void setTime_comment(Date time_comment) {
	this.time_comment = time_comment;
}
public String getId_film() {
	return id_film;
}
public void setId_film(String id_film) {
	this.id_film = id_film;
}
public String getContent_comment() {
	return content_comment;
}
public void setContent_comment(String content_comment) {
	this.content_comment = content_comment;
}

}
