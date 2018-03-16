package com.redworld.mapper;

import com.redworld.model.JobRecommendOnlineStar;
import com.redworld.model.OnlineStar;
import com.redworld.model.OnlineStarExample;
import com.redworld.model.OnlineStarLabel;
import com.redworld.model.OnlineStarRecommend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OnlineStarMapper {
	
    long countByExample(OnlineStarExample example);

    int deleteByExample(OnlineStarExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OnlineStar record);
    
    int insertLabel(OnlineStarLabel label);

    int insertSelective(OnlineStar record);

    List<OnlineStar> selectByExample(OnlineStarExample example);
    
    List<OnlineStarRecommend> selectPopular();
    
    List<OnlineStarRecommend> selectFresh();

    OnlineStar selectByPrimaryKey(Long id);
    
    List<OnlineStarLabel> selectLabelByOsId(Long osId);
    
    List<OnlineStarLabel> selectLabelByName(OnlineStarLabel label);
    
    List<JobRecommendOnlineStar> selectRecommend(@Param("startRow")int startRow, 
			@Param("pageSize")int pageSize, @Param("cityId")int cityId);

    int updateByExampleSelective(@Param("record") OnlineStar record, @Param("example") OnlineStarExample example);

    int updateByExample(@Param("record") OnlineStar record, @Param("example") OnlineStarExample example);

    int updateByPrimaryKeySelective(OnlineStar record);

    int updateByPrimaryKey(OnlineStar record);
}