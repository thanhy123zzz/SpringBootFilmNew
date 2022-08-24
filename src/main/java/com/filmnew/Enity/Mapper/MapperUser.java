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
		user.setAvatar(rs.getString("avatar"));
		user.setGender(rs.getString("gender"));
		user.setFullname(rs.getString("fullname"));
		user.setEmail(rs.getString("email"));
		user.setNumberphone(rs.getString("phone"));
		user.setDateofbirth(rs.getString("dateofbirth"));
		user.setIdfunction(rs.getInt("id_function"));
		return user;
	}
}
