package com.rongzhe.demo.dto;

public class UserEmailDTO {

	private String id;
	private String email;
	private Boolean verified;
	private Boolean activation;
	private String userId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public Boolean getActivation() {
		return activation;
	}

	public void setActivation(Boolean activation) {
		this.activation = activation;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
