package com.filmnew.Service;

import java.util.List;

import com.filmnew.Enity.User;

public interface UserService {
	int delete(String id);
	int add(User user);
	User finpOne(String name);
	int update(User user);
	List<User> findAll();
}
