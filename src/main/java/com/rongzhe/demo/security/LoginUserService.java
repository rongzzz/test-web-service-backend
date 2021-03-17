package com.rongzhe.demo.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rongzhe.demo.dao.UserDAO;
import com.rongzhe.demo.mappers.UserMapper;

@Service
public class LoginUserService implements UserDetailsService {

	@Autowired
	UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("LoginUserService loadUserByUsername " + username);

		final UserDAO userDAO = userMapper.getOneByAccount(username);

		return new User(userDAO.getAccount(), userDAO.getPassword(), Collections.emptyList());
	}

}
