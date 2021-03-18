package com.rongzhe.demo.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rongzhe.demo.dao.User;

@Mapper
public interface UserMapper {

	List<User> getAll();

	// UserDao getOne(Long id);

	User getOneByAccount(String account);

}
