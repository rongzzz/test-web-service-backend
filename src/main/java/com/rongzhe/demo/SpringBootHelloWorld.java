package com.rongzhe.demo;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rongzhe.demo.mappers.UserMapper;

@RestController
public class SpringBootHelloWorld {

	@Autowired
	SqlSession sqlSession;
	@Autowired
	UserMapper userMapper;

	@Value("${spring.datasource.url}")
	String dburl;
	@Value("${spring.datasource.password}")
	String dbPassword;

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String hello() {
		System.out.println("aaaaaaaa");
		return "Hey, Spring Boot 的 Hello World ! ";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> test() {

		System.out.println("dburl = " + dburl);
		System.out.println("dbPassword = " + dbPassword);

		final Map<String, String> test = new HashMap<String, String>();
		test.put("k1", "v1");
		test.put("k2", "v2");
		test.put("k3", "v3");
		test.put("k4", "v4");
		return test;
	}

	@RequestMapping(value = "/mybatistest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object myBatisTest() {
//		return sqlSession.getMapper(UserMapper.class).getAll();
		System.out.println("asdasdasdasd");
		return userMapper.getAll();
	}

}
