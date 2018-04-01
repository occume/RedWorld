package com.redworld.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redworld.mapper.AlipayResultMapper;
import com.redworld.mapper.CommonMapper;
import com.redworld.mapper.ImageMapper;
import com.redworld.model.AlipayResult;
import com.redworld.model.BannerImage;
import com.redworld.model.City;
import com.redworld.model.District;
import com.redworld.model.Feedback;
import com.redworld.model.Group;
import com.redworld.model.Image;
import com.redworld.model.JobType;
import com.redworld.model.Order;
import com.redworld.model.Province;
import com.redworld.model.WalletBankcardType;

@Service
public class CommonService {
	
	@Autowired
	private CommonMapper commonMapper;
	@Autowired
	private ImageMapper workMapper;
	@Autowired
	private AlipayResultMapper alipayResultMapper;
	
	public void saveImage(Image image){
		workMapper.insert(image);
	}
	
	public void saveFiles(List<Image> images){
		for(Image work: images){
			saveImage(work);
		}
	}
	
	public List<JobType> getJobTypeList(){
		return commonMapper.jobTypeList();
	}
	
	public List<Group> getGroupList(){
		return commonMapper.groupList();
	}
	
	public List<City> getCityList(){
		return commonMapper.cityList();
	}
	
	public List<City> getCityList(int provinceId){
		return commonMapper.cityListByProvinceId(provinceId);
	}
	
	public List<District> getDistrictList(int cityId){
		return commonMapper.districtListByCityId(cityId);
	}
	
	public List<Province> getProvinceList(){
		return commonMapper.provinceList();
	}
	
	public List<BannerImage> getBannerImageList(){
		return commonMapper.bannerImageList();
	}
	
	public List<WalletBankcardType> getBankcardTypeList(String prefix){
		return commonMapper.bankcardTypeList(prefix);
	}
	
	public int feedbackAdd(Feedback feedback){
		return commonMapper.feedbackAdd(feedback);
	}
	
	public int createOrder(Order order){
		return commonMapper.createOrder(order);
	}
	
	public int updateOrder(Order order){
		return commonMapper.updateOrder(order);
	}
	
	public int updateJobStatus(long jobId, int statusId){
		return commonMapper.updateJobStatus(jobId, statusId);
	}
	
	public int createAlipayResult(AlipayResult result){
		return alipayResultMapper.insert(result);
	}
	
	public String getPhoneByAuthId(long authId){
		return commonMapper.selectPhoneByAuthId(authId);
	}
}
