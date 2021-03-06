package com.rongzhe.demo.entitiy;

import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sys_user")
@Access(AccessType.FIELD)
public class User extends EntityBase {

	@Column(nullable = false, unique = true)
	private String account;

	@Column(nullable = false)
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_detail_id")
	private UserDetail userDetail;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<UserEmail> userEmails;

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

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public Set<UserEmail> getUserEmails() {
		return userEmails;
	}

	public void setUserEmails(Set<UserEmail> userEmails) {
		this.userEmails = userEmails;
	}

}
