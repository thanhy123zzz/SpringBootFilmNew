package com.filmnew.Dao;

import java.util.List;

import com.filmnew.Enity.User;

public interface UserDao {
	int add(User user);
	
	List<User> findOne(String name);
	
	int delete(String name);
	
	int update(User user);
	
	List<User> findAll();
}
