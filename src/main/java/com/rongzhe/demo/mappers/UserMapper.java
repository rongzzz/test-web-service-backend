package com.rongzhe.demo.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rongzhe.demo.dao.UserDAO;

@Mapper
public interface UserMapper {

	List<UserDAO> getAll();

	// UserDao getOne(Long id);

	UserDAO getOneByAccount(String account);

}
