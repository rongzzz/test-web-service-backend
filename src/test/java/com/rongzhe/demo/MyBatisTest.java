package com.rongzhe.demo;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rongzhe.demo.entitiy.User;
import com.rongzhe.demo.mappers.UserMapper;

@SpringBootTest
public class MyBatisTest {

	@Value("${spring.datasource.username}")
	private String dbUser;

	@Test
	public void connectionTest() {
		System.out.println("aaaaaa");
		System.out.println("dbUser = " + dbUser);
		try (final Reader reader = Resources.getResourceAsReader("mybatis-config.xml");) {
//			System.out.println("test read file :" + (char) reader.read());
//			System.out.println("test read file :");
//			while (reader.read() != -1) {
//				System.out.print((char) reader.read());
//
//			}
			final SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			final SqlSession session = sqlSessionFactory.openSession();
			final List<User> users = session.getMapper(UserMapper.class).getAll();
			final ObjectMapper om = new ObjectMapper();
			System.out.println("test get user : " + om.writeValueAsString(users));
			session.close();
		} catch (Exception e) {
			System.out.println("test fail");
			e.printStackTrace();
		}
	}

}
