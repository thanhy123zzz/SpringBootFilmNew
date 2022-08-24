package com.filmnew.Dao;

import java.util.List;

import com.filmnew.Enity.User;

public interface UserDao {
	int add(User user);
	
	List<User> findOne(String name);

	List<User> findlistbyname(String name);

	List<User> pageofUser(int page);
	
	int delete(String name);
	
	int update(User user);
	
	List<User> findAll();

	int signup(User user);

	List<User> login(String name, String pass);

	//Boolean checkExistUser(String name);

	int updateUser(User user); // update for user

}
