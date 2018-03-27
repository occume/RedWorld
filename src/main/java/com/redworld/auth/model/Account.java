package com.redworld.auth.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
	
	@Id
	@GeneratedValue
	private long id;
	
	@JsonProperty("auth_id")
	private long authId;
	
	@NotNull
	@Size(min=1, max=20)
	private String name;
	
	@Column(name = "province_id")
	@JsonProperty("province_id")
	private int provinceId;
	
	@JsonProperty("province_name")
	private String provinceName;
	
	@JsonProperty("city_id")
	private int cityId;
	
	@JsonProperty("city_name")
	private String cityName;
	
	@JsonProperty("gender_id")
	private int genderId;
	
	@JsonProperty("avatar_image_name")
	private String avatarImageName;
	
	@JsonProperty("group_id")
	private int groupId;
	
	private String registionid;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	
	public long getAuthId() {
		return authId;
	}
	public void setAuthId(long authId) {
		this.authId = authId;
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
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getRegistionid() {
		return registionid;
	}
	public void setRegistionid(String registionid) {
		this.registionid = registionid;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	@Override
	public String toString() {
		return "Account [authId=" + authId + ", name=" + name + ", genderId="
				+ genderId + ", avatarImageName=" + avatarImageName + ", groupId="
				+ groupId + "]";
	}
	
}
