package com.filmnew.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.filmnew.Enity.User;
import com.filmnew.Enity.Mapper.MapperUser;

@Repository
public class UserDaoImp implements UserDao{

	@Autowired
	JdbcTemplate jdbctemplate;
	@Override
	public int add(User user) {
		String sql = "insert into user(name,pass) values(?,?)";
		return jdbctemplate.update(sql, new Object[] {user.getName(),user.getPass()});
	}
	@Override
	public List<User> findOne(String name) {
		String sql = "select*from user where name = '"+name+"'";
		List<User> user = new ArrayList<User>();
		user = jdbctemplate.query(sql, new MapperUser());
		return user;
	}
	
}
