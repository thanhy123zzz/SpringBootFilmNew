package com.filmnew.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.filmnew.Enity.User;
import com.filmnew.Enity.UserComments;
import com.filmnew.Enity.Mapper.MapperCmt;
import com.filmnew.Enity.Mapper.MapperUser;

@Repository
public class UserCommentDaoImp implements UserCommentDao{
	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public Void insert(UserComments cmt) {
		String query = "insert into comment(name,id_film,content_comment,time_comment) values (?,?,?,current_timestamp());";
		jdbctemplate.update(query,new Object[] { cmt.getName(), cmt.getId_film(), cmt.getContent_comment()});
		return null;
	}

	@Override
	public List<UserComments> getCMT(String id) {
		String query = "select*from comment where id_film = "+id+" order by time_comment DESC;";
		List<UserComments> list = new ArrayList<UserComments>();
		list = jdbctemplate.query(query, new MapperCmt());
		return list;
	}
	
}
