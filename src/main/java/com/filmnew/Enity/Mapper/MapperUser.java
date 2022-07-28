package com.filmnew.Enity.Mapper;

import org.springframework.jdbc.core.RowMapper;

import com.filmnew.Enity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperUser implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setName(rs.getString("name"));
		user.setPass(rs.getString("pass"));
		return user;
	}
}
