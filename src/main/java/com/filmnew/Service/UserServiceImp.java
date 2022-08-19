package com.filmnew.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmnew.Dao.UserDao;
import com.filmnew.Enity.User;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public int delete(String name) {
		return userDao.delete(name);
	}

	@Override
	public int add(User user) {
		return userDao.add(user);
	}

	@Override
	public User finpOne(String name) {
		return userDao.findOne(name).get(0);
	}

	@Override
	public int update(User user) {
		return userDao.update(user);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public List<User> findlistbyname(String name){
		return userDao.findlistbyname(name);
	}

	/*@Override
	public Boolean checkExistUser(String name){
		return userDao.checkExistUser(name);
	}
 */
	@Override
	public List<User> pageofUser(int page){
		return userDao.pageofUser(page);
	}

}
