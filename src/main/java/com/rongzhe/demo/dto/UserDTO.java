package com.rongzhe.demo.dto;

import com.rongzhe.demo.entitiy.User;

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

	public static UserDTO transferToDTO(User user) {
		final UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setAccount(user.getAccount());
		userDTO.setPassword(user.getPassword());
		return userDTO;
	}

}
