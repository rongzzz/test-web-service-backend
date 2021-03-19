package com.rongzhe.demo.entitiy;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.logging.log4j.util.Strings;

@MappedSuperclass
public abstract class EntityBase {

	EntityBase() {
		if (Strings.isEmpty(id)) {
			final UUID uuid = UUID.randomUUID();
			id = uuid.toString();
		}
	}

	@Id
	@Column
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
