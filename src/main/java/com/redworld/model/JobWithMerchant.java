package com.redworld.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JobWithMerchant {
	private Long id;

    @JsonProperty("auth_id")
    private Long authId;

    private String title;

    private String content;

    @JsonProperty("job_type_id")
    private Integer jobTypeId;
    
    @JsonProperty("job_type_name")
    private String jobTypeName;

    @JsonProperty("city_id")
    private Integer cityId;
    
    @JsonProperty("job_city_name")
    private String jobCityName;
    
    @JsonProperty("start_time")
    private Date startTime;
    
    @JsonProperty("end_time")
    private Date endTime;

    private String address;

    @JsonProperty("expect_count")
    private Integer expectCount;

    private Float price;

    @JsonProperty("gender_id")
    private Integer genderId;

    private String phone;

    @JsonProperty("status_id")
    private Integer statusId;
    
    @JsonProperty("merchant_name")
    private String merchantName;
    
    @JsonProperty("merchant_avatar")
    private String avatarImageName;
    
    private Apply apply;
    
    @JsonProperty("image_list")
    private List<JobImage> imageList;
    
    @Override
	public String toString() {
		return "JobWithMerchant [id=" + id + ", title=" + title
				+ ", expectCount=" + expectCount + ", phone=" + phone
				+ ", merchantName=" + merchantName + ", cityName=" + cityName
				+ ", provinceName=" + provinceName + "]";
	}

	@JsonProperty("merchant_city_name")
    private String cityName;
    
    @JsonProperty("merchant_province_name")
    private String provinceName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAuthId() {
		return authId;
	}

	public void setAuthId(Long authId) {
		this.authId = authId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getJobTypeId() {
		return jobTypeId;
	}

	public void setJobTypeId(Integer jobTypeId) {
		this.jobTypeId = jobTypeId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getExpectCount() {
		return expectCount;
	}

	public void setExpectCount(Integer expectCount) {
		this.expectCount = expectCount;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getGenderId() {
		return genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getAvatarImageName() {
		return avatarImageName;
	}

	public void setAvatarImageId(String avatarImageId) {
		this.avatarImageName = avatarImageId;
	}

	public List<JobImage> getImageList() {
		return imageList;
	}

	public void setImageList(List<JobImage> imageList) {
		this.imageList = imageList;
	}

	public Apply getApply() {
		return apply;
	}

	public void setApply(Apply apply) {
		this.apply = apply;
	}

	public String getJobTypeName() {
		return jobTypeName;
	}

	public void setJobTypeName(String jobTypeName) {
		this.jobTypeName = jobTypeName;
	}

	public String getJobCityName() {
		return jobCityName;
	}

	public void setJobCityName(String jobCityName) {
		this.jobCityName = jobCityName;
	}

	public void setAvatarImageName(String avatarImageName) {
		this.avatarImageName = avatarImageName;
	}

}
