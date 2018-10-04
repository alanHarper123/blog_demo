package com.qiujintao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiujintao.mapper.CommentMapper;
import com.qiujintao.mapper.UserMapper;
import com.qiujintao.model.Comment;
import com.qiujintao.model.CommentExample;

@Service
public class CommentService {
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private UserMapper userMapper;
	
	public List<Comment> selectDepthOneCommentsByArticleID(int articleID){
		CommentExample commentExample = new CommentExample();
		commentExample.createCriteria().andArticle_idEqualTo(articleID).andReply_to_comment_idIsNull();
		List<Comment> result = commentMapper.selectByExample(commentExample);
		for(Comment comment : result) {
			comment.setUser(userMapper.selectByPrimaryKey(comment.getUser_id()));
		}
		return result;
		
	}
}
