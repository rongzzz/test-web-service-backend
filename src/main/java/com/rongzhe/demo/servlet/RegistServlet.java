package com.rongzhe.demo.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rongzhe.demo.dao.UserJpaRepository;
import com.rongzhe.demo.dto.UserDTO;
import com.rongzhe.demo.dto.UserEmailDTO;
import com.rongzhe.demo.entitiy.User;
import com.rongzhe.demo.entitiy.UserDetail;
import com.rongzhe.demo.entitiy.UserEmail;

@WebServlet("/regist")
public class RegistServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final static Logger LOGGER = LoggerFactory.getLogger(RegistServlet.class);

	@Autowired
	UserJpaRepository userJpaRepository;
	// @Autowired
	// PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		final String body = bodyContent(request.getReader());
		LOGGER.debug("body = {}", body);

		final ObjectMapper om = new ObjectMapper();
		final UserDTO userDTO = om.readValue(body, UserDTO.class);

		final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		final User user = new User();
		final UserDetail userDetail = new UserDetail();
		final Set<UserEmail> emails = new HashSet<>();
		user.setAccount(userDTO.getAccount());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		userDetail.setDisplayName(userDTO.getUserDetail().getDisplayName());
		userDetail.setFirstName(userDTO.getUserDetail().getFirstName());
		userDetail.setLastName(userDTO.getUserDetail().getLastName());
		for (final UserEmailDTO userEmailDTO : userDTO.getUserEmails()) {
			final UserEmail email = new UserEmail();
			email.setActivation(true);
			email.setVerified(false);
			email.setEmail(userEmailDTO.getEmail());
			email.setUser(user);
			emails.add(email);
		}
		user.setUserEmails(emails);
		user.setUserDetail(userDetail);
		userJpaRepository.save(user);
	}

	private String bodyContent(BufferedReader reader) throws IOException {
		String input = null;
		final StringBuilder requestBody = new StringBuilder();
		while ((input = reader.readLine()) != null) {
			requestBody.append(input);
		}
		return requestBody.toString();
	}
}
