package com.filmnew.Service;

import java.util.List;

import com.filmnew.Enity.User;

public interface UserService {
	int delete(String name);
	int add(User user);
	User finpOne(String name);
	int update(User user);
	List<User> findAll();
	List<User> findlistbyname(String name);
	List<User> pageofUser(int page);
	List<User> login(String name, String pass);
	//Boolean checkExistUser(String name);
}
