package com.rongzhe.demo.servlet;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class ProcessEverythingFilter extends HttpFilter {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		long begin = System.currentTimeMillis();
		chain.doFilter(request, response);
		getServletContext().log("Request " + request.getRequestURI() + " process in "
				+ (System.currentTimeMillis() - begin) + " milliseconds");
	}

}
