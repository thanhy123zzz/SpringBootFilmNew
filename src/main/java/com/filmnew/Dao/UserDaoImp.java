package com.filmnew.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.filmnew.Enity.User;
import com.filmnew.Enity.Mapper.MapperUser;

@Repository
public class UserDaoImp implements UserDao {

	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public int add(User user) {
		String sql = "INSERT INTO `user`(`name`,`pass`,`fullname`,`email`,`phone`,`gender`,`dateofbirth`,`avatar`,`id_function`) VALUES(?,?,?,?,?,?,?,?,?)";
		return jdbctemplate.update(sql,
				new Object[] { user.getName(), user.getPass(), user.getFullname(), user.getEmail(),
						user.getNumberphone(), user.getGender(), user.getDateofbirth(), user.getAvatar(),
						user.getIdfunction() });
	}

	@Override
	public List<User> findOne(String name) {
		String sql = "select * from user where name = '" + name + "'";
		List<User> user = new ArrayList<User>();
		user = jdbctemplate.query(sql, new MapperUser());
		User user1 = new User("", "");
		if (user.size() < 1) {
			user.add(user1);
		}
		return user;
	}

	@Override
	public List<User> findlistbyname(String name) {
		String sql = "select * from user where name Like '%" + name + "%'";
		List<User> user = new ArrayList<User>();
		user = jdbctemplate.query(sql, new MapperUser());
		return user;
	}

	@Override
	public int delete(String name) {
		String sql = "delete from user where name = '" + name + "'";
		return jdbctemplate.update(sql);
	}

	@Override
	public int update(User user) {
		String sql = "UPDATE `movies`.`user` SET `pass` = ?, `fullname` = ?, `email` = ?, `phone` = ?, `gender` = ?, `dateofbirth` =?, `avatar` = ?, `id_function` = ? WHERE `name` = ?";
		return jdbctemplate.update(sql,
				new Object[] { user.getPass(), user.getFullname(), user.getEmail(), user.getNumberphone(),
						user.getGender(), user.getDateofbirth(), user.getAvatar(), user.getIdfunction(),
						user.getName() });
	}

	@Override
	public List<User> findAll() {
		String sql = "select * from user";
		List<User> user = new ArrayList<User>();
		user = jdbctemplate.query(sql, new MapperUser());
		return user;
	}

	@Override
	public List<User> pageofUser(int page) {
		// SELECT * FROM user ORDER BY name ASC LIMIT 0,8
		String sql = "SELECT * FROM user ORDER  BY name ASC LIMIT " + page + ",8";
		List<User> list = new ArrayList<User>();
		list = jdbctemplate.query(sql, new MapperUser());
		return list;
	}

	@Override
	public List<User> login(String name, String pass) {
		String sql = "SELECT * FROM user WHERE name = '" + name + "' AND pass = '" + pass + "' ";
		List<User> user = jdbctemplate.query(sql, new MapperUser());
		User user1 = new User("", "");
		if (user.size() < 1) {
			user.add(user1);
		}
		return user;
	}

	@Override
	public int signup(User user) {
		String sql = "INSERT INTO `user` (`name`, `pass`) VALUES (?,?)";
		return jdbctemplate.update(sql, new Object[] { user.getName(), user.getPass() });
	}

	@Override
	public int updateUser(User user){
		String sql = "UPDATE `user` SET `fullname` = ?, `email` = ?, `phone` = ?, `gender` = ?, `dateofbirth` = ?, `avatar` = ? WHERE (`name` = ?)";
		return jdbctemplate.update(sql, new Object[] { user.getFullname(), user.getEmail(),user.getNumberphone(),user.getGender(),user.getDateofbirth(),user.getAvatar(),user.getName() });
	}

	/*
	 * @Override
	 * public Boolean checkExistUser(String name) {
	 * String sql = "select * from user where name = '" + name + "'";
	 * List<User> list = jdbctemplate.query(sql, new MapperUser());
	 * User user = list.get(0);
	 * if (user.getName().equals(name)) {
	 * return true;
	 * } else {
	 * return false;
	 * }
	 * }
	 */

}
