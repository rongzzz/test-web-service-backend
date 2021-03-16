package com.rongzhe.demo.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	private final String LOGGED_IN = "logged_in";
	private final String USER_TYPE = "user_type";

	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication)
			throws IOException, ServletException {
		final String account = authentication.getName();
		final Collection<?> collection = authentication.getAuthorities();
		final String authority = collection.iterator().next().toString();
		final HttpSession session = req.getSession();
		session.setAttribute(LOGGED_IN, account);
		session.setAttribute(USER_TYPE, authority);
		final Map<String, String> result = new HashMap<>();
		result.put("authority", authority);
		resp.setContentType("application/json;charset=UTF-8");
		resp.setStatus(200);
		final PrintWriter out = resp.getWriter();
		final ObjectMapper om = new ObjectMapper();
		out.write(om.writeValueAsString(result));
		out.flush();
		out.close();
	}
}
