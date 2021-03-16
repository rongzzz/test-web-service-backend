package com.rongzhe.demo.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException e) throws IOException, ServletException {
		final ObjectMapper mapper = new ObjectMapper();
		final Map<String, String> map = Map.of("error", "請先登入才能進行此操作");
		final String error = mapper.writeValueAsString(map);
		httpServletResponse.setContentType("application/json;charset=UTF-8");
		httpServletResponse.setCharacterEncoding("UTF-8");
		httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		final PrintWriter writer = httpServletResponse.getWriter();
		writer.write(error);
		writer.flush();
		writer.close();
	}
}
