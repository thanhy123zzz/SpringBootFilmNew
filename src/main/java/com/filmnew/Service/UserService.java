package com.filmnew.Service;

import com.filmnew.Enity.User;

public interface UserService {
	void delete(String id);
	int add(User user);
	User finpOne(String name);
}
