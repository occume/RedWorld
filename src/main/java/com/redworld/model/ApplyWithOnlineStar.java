package com.redworld.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplyWithOnlineStar {

	private long id;
	private String name;
	
	@JsonProperty("gender_id")
	private int genderId;
	
	@JsonProperty("avatar_image_name")
	private String avatarImageName;
	
	@JsonProperty("status_id")
	private int statusId;
	
	@JsonProperty("auth_id")
	private int authId;

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

	public String getAvatarImageName() {
		return avatarImageName;
	}

	public void setAvatarImageName(String avatarImageName) {
		this.avatarImageName = avatarImageName;
	}

	public int getAuthId() {
		return authId;
	}

	public void setAuthId(int authId) {
		this.authId = authId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

}
