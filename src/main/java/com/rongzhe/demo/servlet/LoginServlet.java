package com.rongzhe.demo.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rongzhe.demo.dao.UserDAO;
import com.rongzhe.demo.dto.UserDTO;
import com.rongzhe.demo.mappers.UserMapper;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	UserMapper userMapper;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		final String body = bodyContent(request.getReader());
		System.out.println("body = " + body);
		final ObjectMapper om = new ObjectMapper();
		final UserDTO user = om.readValue(body, UserDTO.class);
		final UserDAO userDAO = userMapper.getOneByAccount(user.getAccount());
		if (userDAO != null && userDAO.getPassword().equals(user.getPassword())) {
			response.getWriter().print("ok");
		} else {
			response.getWriter().print("no");
		}
	}

	private String bodyContent(BufferedReader reader) throws IOException {
		String input = null;
		StringBuilder requestBody = new StringBuilder();
		while ((input = reader.readLine()) != null) {
			requestBody.append(input);
		}
		return requestBody.toString();
	}

}
