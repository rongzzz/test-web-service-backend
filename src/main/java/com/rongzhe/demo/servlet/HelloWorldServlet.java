package com.rongzhe.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/helloworld")
public class HelloWorldServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("HelloWorldServlet do Get");
		PrintWriter out = response.getWriter();
		out.println("HAHAHA");
		out.println("___________");
	}

}
