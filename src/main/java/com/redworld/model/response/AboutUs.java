package com.redworld.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AboutUs {
	
	public static final AboutUs about = new AboutUs("Hongrz", "1041200466", "1041200466@163.com", "",
			"copy@2017-2018 redworld.red", "上海娇娜文化传播有限公司");
	
	private AboutUs(String wechat, String qq, String email, String intro, String copyright,
			String companyName) {
		this.wechat = wechat;
		this.qq = qq;
		this.email = email;
		this.intro = intro;
		this.copyright = copyright;
		this.companyName = companyName;
	}

	public String wechat;
	public String qq;
	public String email;
	public String intro;
	public String copyright;
	
	@JsonProperty("conpany_name")
	public String companyName;
}
