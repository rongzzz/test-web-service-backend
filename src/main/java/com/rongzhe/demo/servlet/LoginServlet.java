// package com.rongzhe.demo.servlet;
//
// import java.io.BufferedReader;
// import java.io.IOException;
//
// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
//
// import org.springframework.beans.factory.annotation.Autowired;
//
// import com.rongzhe.demo.mappers.UserMapper;
//
// @WebServlet("/login")
// public class LoginServlet extends HttpServlet {
//
// private static final long serialVersionUID = 1L;
//
// @Autowired
// UserMapper userMapper;
//
// @Override
// protected void doPost(HttpServletRequest request, HttpServletResponse
// response)
// throws IOException, ServletException {
// // final String body = bodyContent(request.getReader());
// // System.out.println("body = " + body);
// // final ObjectMapper om = new ObjectMapper();
// // final UserDTO user = om.readValue(body, UserDTO.class);
// // final UserDAO userDAO = userMapper.getOneByAccount(user.getAccount());
// // final UserDTO ret = UserDTO.transferToDTO(userDAO);
// // response.getWriter().print(om.writeValueAsString(ret));
// }
//
//// private String bodyContent(BufferedReader reader) throws IOException {
//// String input = null;
//// final StringBuilder requestBody = new StringBuilder();
//// while ((input = reader.readLine()) != null) {
//// requestBody.append(input);
//// }
//// return requestBody.toString();
//// }
//
// }
