package com.rongzhe.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rongzhe.demo.entitiy.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, String> {

	User findByAccount(String account);

}
