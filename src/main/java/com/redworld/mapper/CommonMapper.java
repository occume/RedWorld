package com.redworld.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.redworld.model.BannerImage;
import com.redworld.model.City;
import com.redworld.model.District;
import com.redworld.model.Feedback;
import com.redworld.model.Group;
import com.redworld.model.JobType;
import com.redworld.model.Order;
import com.redworld.model.Province;
import com.redworld.model.WalletBankcard;
import com.redworld.model.WalletBankcardType;
import com.redworld.model.response.WalletBankcardResponse;

public interface CommonMapper {

	List<City> cityList();
	
	List<City> cityListByProvinceId(int provinceId);
	
	List<District> districtListByCityId(int cityId);
	
	List<Province> provinceList();
	
	List<Group> groupList();
	
	List<JobType> jobTypeList();
	
	List<BannerImage> bannerImageList();
	
	List<WalletBankcardType> bankcardTypeList(@Param("prefix")String prefix);
	
	int feedbackAdd(Feedback feedback);
	
	int createOrder(Order order);
	
	int updateOrder(Order order);
	
	int updateJobStatus(@Param("jobId") long jobId, @Param("statusId") long statusId);
	
	List<WalletBankcardResponse> bankcardList(WalletBankcard bankCard);
	
	String selectPhoneByAuthId(long authId);
}
