package com.redworld.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplyWithAccount {

	private long id;
	private String name;
	
	@JsonProperty("gender_id")
	private int genderId;
	
	@JsonProperty("avatar_image_id")
	private int avatarImageId;
	
	@JsonProperty("status_id")
	private int statusId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getGenderId() {
		return genderId;
	}

	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}

	public int getAvatarImageId() {
		return avatarImageId;
	}

	public void setAvatarImageId(int avatarImageId) {
		this.avatarImageId = avatarImageId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

}
