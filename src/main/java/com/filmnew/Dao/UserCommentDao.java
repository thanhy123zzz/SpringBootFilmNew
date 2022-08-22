package com.filmnew.Dao;

import java.util.List;

import com.filmnew.Enity.UserComments;

public interface UserCommentDao {
	Void insert(UserComments cmt);
	List<UserComments> getCMT(String id);
}
