package com.filmnew.Service;

import java.sql.Blob;
import java.util.List;

import com.filmnew.Enity.UserComments;

public interface UserCommentService {
void insertComment(UserComments cmt);
List<UserComments> getComment(String idfilm);

Blob getVideo();
}
