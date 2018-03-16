package com.redworld.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redworld.ParameterProcessor;
import com.redworld.mapper.ApplyMapper;
import com.redworld.mapper.JobImageMapper;
import com.redworld.mapper.JobMapper;
import com.redworld.model.Apply;
import com.redworld.model.ApplyExample;
import com.redworld.model.Job;
import com.redworld.model.JobExample;
import com.redworld.model.JobImage;
import com.redworld.model.JobImageExample;
import com.redworld.model.JobWithMerchant;
import com.redworld.model.JobExample.Criteria;
import com.redworld.model.response.JobResponse;
import com.redworld.model.response.MerchantResponse;

@Service
public class JobService extends ParameterProcessor{

	@Autowired
	private JobMapper jobMapper;
	@Autowired
	private JobImageMapper imageMapper;
	@Autowired
	private ApplyMapper applyMapper;
	
	public int insert(Job job){
		return jobMapper.insert(job);
	}
	
	public int update(Job job){
		return jobMapper.updateByPrimaryKeySelective(job);
	}
	
	public int insert(JobImage image){
		return imageMapper.insert(image);
	}
	
	public List<JobResponse> jobListOfMerchant(int page, Map<String, Object> params){

		pageSet(page, params);
		
		List<JobResponse> jobList = jobMapper.selectJobListOfOs(params);
		for(JobResponse job: jobList){
			JobImageExample imageExample = new JobImageExample();
			JobImageExample.Criteria c = imageExample.createCriteria();
			c.andJobIdEqualTo(job.getId());
			List<JobImage> imageList = imageMapper.selectByExample(imageExample);
			job.setImageList(imageList);
		}
		return jobList;
	}
	
	public List<Job> jobList(Map<String, Object> params){
		JobExample example = new JobExample();
		Criteria c = example.createCriteria();
		
		if(params.containsKey("city_id")){
			int cityId = Integer.valueOf(params.get("city_id").toString());
			c.andCityIdEqualTo(cityId);
		}
		
		if(params.containsKey("job_type_id")){
			int jobTypeId = Integer.valueOf(params.get("job_type_id").toString());
			c.andJobTypeIdEqualTo(jobTypeId);
		}
		
		if(params.containsKey("auth_id")){
			long merchantId = Integer.valueOf(params.get("auth_id").toString());
			c.andAuthIdEqualTo(merchantId);
		}
		
		return jobMapper.selectByExample(example);
	}
	
	public List<JobResponse> jobListOfOs(Map<String, Object> params){
		int page = getParamInt("page", params);
		pageSet(page, params);
		
		
		
		return jobMapper.selectJobListOfOs(params);
	}
	
	public JobWithMerchant jobDetail(long jobId, long authId){
		JobImageExample imageExample = new JobImageExample();
		imageExample.createCriteria().andJobIdEqualTo(jobId);
		List<JobImage> jobImageList = imageMapper.selectByExample(imageExample);
		JobWithMerchant jobWithMerchant = jobMapper.selectWithMerchant(jobId);
		jobWithMerchant.setImageList(jobImageList);
		
		ApplyExample applyExample = new ApplyExample();
		ApplyExample.Criteria c = applyExample.createCriteria();
		c.andJobIdEqualTo(jobId);
		c.andAuthIdEqualTo(authId);
		List<Apply> applyList = applyMapper.selectByExample(applyExample);
		Apply apply = applyList.size() > 0 ? applyList.get(0) : null;
		
		jobWithMerchant.setApply(apply);
		return jobWithMerchant;
	}
	
	public MerchantResponse jobMerchant(long jobId, long authId){
		
		MerchantResponse jobWithMerchant = jobMapper.selectWithMerchantResponse(jobId);
		
		ApplyExample applyExample = new ApplyExample();
		ApplyExample.Criteria c = applyExample.createCriteria();
		c.andJobIdEqualTo(jobId);
		c.andAuthIdEqualTo(authId);
		List<Apply> applyList = applyMapper.selectByExample(applyExample);
		Apply apply = applyList.size() > 0 ? applyList.get(0) : null;
		
		jobWithMerchant.setApply(apply);
		
		return jobWithMerchant;
	}
	
	public Job getById(long id){
		return jobMapper.selectByPrimaryKey(id);
	}
}
