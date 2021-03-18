package com.rongzhe.demo.dto;

import com.rongzhe.demo.dao.UserDAO;

public class UserDTO {

	private String id;
	private String account;
	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static UserDTO transferToDTO(UserDAO userDAO) {
		final UserDTO userDTO = new UserDTO();
		userDTO.setId(userDAO.getId());
		userDTO.setAccount(userDAO.getAccount());
		userDTO.setPassword(userDAO.getPassword());
		return userDTO;
	}

}
