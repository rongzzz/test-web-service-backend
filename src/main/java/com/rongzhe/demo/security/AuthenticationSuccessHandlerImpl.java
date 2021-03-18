package com.rongzhe.demo.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rongzhe.demo.dao.User;
import com.rongzhe.demo.dto.UserDTO;
import com.rongzhe.demo.mappers.UserMapper;

@Service
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	private final String LOGGED_IN = "logged_in";
	private final String USER_TYPE = "user_type";

	@Autowired
	private UserMapper userMapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication)
			throws IOException, ServletException {
		final String account = authentication.getName();
		final Collection<?> collection = authentication.getAuthorities();
		final User userDAO = userMapper.getOneByAccount(account);
		final UserDTO userDTO = UserDTO.transferToDTO(userDAO);
		// TODO add authority
		final String authority = collection.iterator().hasNext() ? collection.iterator().next().toString() : null;
		final HttpSession session = req.getSession();
		session.setAttribute(LOGGED_IN, account);
		session.setAttribute(USER_TYPE, authority);
		resp.setContentType("application/json;charset=UTF-8");
		resp.setStatus(200);
		final PrintWriter out = resp.getWriter();
		final ObjectMapper om = new ObjectMapper();
		out.write(om.writeValueAsString(userDTO));
		out.flush();
		out.close();
	}
}
