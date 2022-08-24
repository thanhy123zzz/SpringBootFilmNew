package com.filmnew.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmnew.Dao.UserCommentDao;
import com.filmnew.Dao.UserDao;
import com.filmnew.Enity.UserComments;

@Service
public class UserCommentServiceImp implements UserCommentService{

	@Autowired
	private UserCommentDao userCMTDao;
	
	@Override
	public void insertComment(UserComments cmt) {
		userCMTDao.insert(cmt);
	}

	@Override
	public List<UserComments> getComment(String idfilm) {
		
		return userCMTDao.getCMT(idfilm);
	}

}
