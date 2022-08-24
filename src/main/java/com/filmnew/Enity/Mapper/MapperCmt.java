package com.filmnew.Enity.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.filmnew.Enity.User;
import com.filmnew.Enity.UserComments;

public class MapperCmt implements RowMapper<UserComments>{

	@Override
	public UserComments mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserComments cmt = new UserComments();
		cmt.setContent_comment(rs.getString("content_comment"));
		cmt.setName(rs.getString("name"));
		cmt.setId_film(rs.getString("id_film"));
		cmt.setTime_comment(rs.getDate("time_comment"));
		return cmt;
	}
}
