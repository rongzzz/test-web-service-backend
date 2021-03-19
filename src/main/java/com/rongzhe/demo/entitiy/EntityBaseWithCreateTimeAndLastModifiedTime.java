package com.rongzhe.demo.entitiy;

import java.util.UUID;

import javax.persistence.Id;

import org.apache.logging.log4j.util.Strings;

public class EntityBaseWithCreateTimeAndLastModifiedTime {

	EntityBaseWithCreateTimeAndLastModifiedTime() {
		if (Strings.isEmpty(id)) {
			final UUID uuid = UUID.randomUUID();
			id = uuid.toString();
		}
	}

	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
