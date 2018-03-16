package com.redworld.auth.mapper;

import org.apache.ibatis.annotations.Param;

import com.redworld.auth.model.Auth;

public interface AuthMapper {

	void save(Auth auth);
	
	void resetPassword(Auth auth);
	
	int resetToken(Auth auth);
	
	Auth getAuthByPhone(String phone);
	
	Auth getAuth(@Param(value="phone")String phone, 
	  		 @Param(value="password")String password);
	
	Auth getByWx(@Param(value="openKey")String openKey);
	
	Auth getByWeibo(@Param(value="openKey")String openKey);
	
	Auth getByQQ(@Param(value="openKey")String openKey);
	
	int bindWx(Auth auth);
	
	int bindWeibo(Auth auth);
	
	int bindQQ(Auth auth);
}
