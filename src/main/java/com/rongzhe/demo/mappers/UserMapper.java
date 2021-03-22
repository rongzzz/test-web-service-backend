package com.rongzhe.demo.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rongzhe.demo.dto.UserReportDTO;
import com.rongzhe.demo.entitiy.User;

@Mapper
public interface UserMapper {

	List<User> getAll();

	// UserDao getOne(Long id);

	User getOneByAccount(String account);

	List<UserReportDTO> getUserReportData();

}
