package com.redworld.mapper;

import com.redworld.model.OnlineStarWork;
import com.redworld.model.OnlineStarWorkExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OnlineStarWorkMapper {
    long countByExample(OnlineStarWorkExample example);

    int deleteByExample(OnlineStarWorkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OnlineStarWork record);

    int insertSelective(OnlineStarWork record);

    List<OnlineStarWork> selectByExample(OnlineStarWorkExample example);
    
    List<OnlineStarWork> selectPopularWorks();
    
    List<OnlineStarWork> selectFreshWorks();
    
    List<OnlineStarWork> selectWorksByOsId(@Param("osId") long osId, @Param("typeId") int typeId);
    
    List<OnlineStarWork> selectWorksOfBroker(@Param("osList") List<Long> osList, @Param("typeId") int typeId);

    OnlineStarWork selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OnlineStarWork record, @Param("example") OnlineStarWorkExample example);

    int updateByExample(@Param("record") OnlineStarWork record, @Param("example") OnlineStarWorkExample example);

    int updateByPrimaryKeySelective(OnlineStarWork record);

    int updateByPrimaryKey(OnlineStarWork record);
}