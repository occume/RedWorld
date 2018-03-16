package com.redworld.mapper;

import com.redworld.model.Apply;
import com.redworld.model.ApplyExample;
import com.redworld.model.ApplyWithAccount;
import com.redworld.model.ApplyWithJob;
import com.redworld.model.ApplyWithOnlineStar;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ApplyMapper {
    long countByExample(ApplyExample example);

    int deleteByExample(ApplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Apply record);

    int insertSelective(Apply record);

    List<Apply> selectByExample(ApplyExample example);

    Apply selectByPrimaryKey(Long id);
    
    Apply selectByJobOnlineStar(@Param("jobId")Long jobId, @Param("authId")Long authId);
    
    List<ApplyWithOnlineStar> selectWithOnlineStar(@Param("jobId")Long jobId);
    
    List<ApplyWithAccount> selectWithAccount(@Param("jobId")Long jobId);
    
    List<ApplyWithJob> selectWithJob(@Param("startRow")int startRow, @Param("pageSize")int pageSize,
    		@Param("authId")Long authId, @Param("statusId")Integer statusId,
    		@Param("typeId")Integer typeId);

    int updateByExampleSelective(@Param("record") Apply record, @Param("example") ApplyExample example);

    int updateByExample(@Param("record") Apply record, @Param("example") ApplyExample example);

    int updateByPrimaryKeySelective(Apply record);

    int updateByPrimaryKey(Apply record);
}