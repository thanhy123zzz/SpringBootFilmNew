package com.filmnew.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmnew.Dao.UserDao;
import com.filmnew.Enity.User;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int add(User user) {
		return userDao.add(user);
	}

	@Override
	public User finpOne(String name) {
		return userDao.findOne(name).get(0);
	}

}
