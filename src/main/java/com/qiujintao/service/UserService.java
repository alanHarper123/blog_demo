package com.qiujintao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiujintao.mapper.UserMapper;
import com.qiujintao.model.User;
import com.qiujintao.model.UserExample;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	public List<User> findAllUsers(){
		UserExample example = new UserExample();
		example.setOrderByClause("id desc");
		return userMapper.selectByExample(example);
	}
	public List<User> findByUserEmaiAndpassWordhash(String email, String passWordhash) {
		UserExample example = new UserExample();
		example.createCriteria().andEmailEqualTo(email).andPasswordhashEqualTo(passWordhash);
		return userMapper.selectByExample(example);
	}
	public void insertUser(User user) {
		userMapper.insert(user);
	}
	public List<User> findUserByEmail(String email) {
		UserExample example = new UserExample();
		example.createCriteria().andEmailEqualTo(email);
		return userMapper.selectByExample(example);
	}
	public boolean isNewUserValid(User newUser) {
		UserExample example = new UserExample();
		example.or().andEmailEqualTo(newUser.getEmail());
		example.or().andNicknameEqualTo(newUser.getNickname());
		if (userMapper.selectByExample(example).isEmpty()) {
			return true;
		}
		return false;
	}
	public void deleteUser(User user) {
		userMapper.deleteByPrimaryKey(user.getId());
	}
}
