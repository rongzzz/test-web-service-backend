package com.rongzhe.demo.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException e) throws IOException, ServletException {
		httpServletResponse.setContentType("application/json;charset=UTF-8");
		final PrintWriter out = httpServletResponse.getWriter();
		httpServletResponse.setStatus(404);
		final Map<String, String> result = Map.of("message", "登入失敗");
		final ObjectMapper om = new ObjectMapper();
		out.write(om.writeValueAsString(result));
		out.flush();
		out.close();
	}
}
