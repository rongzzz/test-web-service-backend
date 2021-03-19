package com.rongzhe.demo.entitiy;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;

import org.apache.logging.log4j.util.Strings;

public class EntityBaseWithCreateTime {

	EntityBaseWithCreateTime() {
		if (Strings.isEmpty(id)) {
			final UUID uuid = UUID.randomUUID();
			id = uuid.toString();
		}
		if (createTime == null) {
			createTime = new Date();
		}
	}

	@Id
	private String id;
	@Column
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
