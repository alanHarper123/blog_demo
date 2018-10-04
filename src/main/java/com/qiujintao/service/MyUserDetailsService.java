package com.qiujintao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.qiujintao.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserService userService;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		List<User> userExists = userService.findUserByEmail(email);
		if(userExists.isEmpty()) {
			throw new UsernameNotFoundException(email);
		}else {
			return new MyUserDetails(userExists.get(0));
		}

	}
	
}
