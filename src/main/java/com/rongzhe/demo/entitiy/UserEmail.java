package com.rongzhe.demo.entitiy;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sys_user_email")
@Access(AccessType.FIELD)
public class UserEmail extends EntityBase {

	@Column(nullable = false)
	private String email;

	@Column(nullable = false, columnDefinition = "boolean default false")
	private Boolean verified;

	@Column(nullable = false, columnDefinition = "boolean default false")
	private Boolean activation;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
