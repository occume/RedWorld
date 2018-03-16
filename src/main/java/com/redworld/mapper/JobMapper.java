package com.redworld.mapper;

import com.redworld.model.Job;
import com.redworld.model.JobExample;
import com.redworld.model.JobWithMerchant;
import com.redworld.model.response.JobResponse;
import com.redworld.model.response.MerchantResponse;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface JobMapper {
	
    long countByExample(JobExample example);

    int deleteByExample(JobExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Job record);

    int insertSelective(Job record);

    List<Job> selectByExample(JobExample example);
    
    List<JobResponse> selectJobListOfOs(Map<String, Object> params);
    
    MerchantResponse selectWithMerchantResponse(@Param("id")Long id);

    Job selectByPrimaryKey(Long id);
    
    JobWithMerchant selectWithMerchant(@Param("id")Long id);

    int updateByExampleSelective(@Param("record") Job record, @Param("example") JobExample example);

    int updateByExample(@Param("record") Job record, @Param("example") JobExample example);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);
}