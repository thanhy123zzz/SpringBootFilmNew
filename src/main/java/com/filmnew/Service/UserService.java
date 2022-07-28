package com.filmnew.Service;

import org.springframework.stereotype.Service;

import com.filmnew.Enity.User;

public interface UserService {
	void delete(String id);
	void add(User user);
}
