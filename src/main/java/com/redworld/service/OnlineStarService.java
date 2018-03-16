package com.redworld.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redworld.Constant;
import com.redworld.mapper.OnlineStarMapper;
import com.redworld.mapper.OnlineStarWorkMapper;
import com.redworld.model.JobRecommendOnlineStar;
import com.redworld.model.OnlineStar;
import com.redworld.model.OnlineStarExample;
import com.redworld.model.OnlineStarLabel;
import com.redworld.model.OnlineStarRecommend;
import com.redworld.model.OnlineStarWork;
import com.redworld.model.OnlineStarWorkExample;

@Service
public class OnlineStarService {
	
	private static final Logger LOG = LoggerFactory.getLogger(OnlineStarService.class);

	@Autowired
	private OnlineStarMapper osMapper;
	@Autowired
	private OnlineStarWorkMapper workMapper;
	
	public int addWork(OnlineStarWork work){
		return workMapper.insert(work);
	}
	
	public int removeWork(long workId){
		return workMapper.deleteByPrimaryKey(workId);
	}
	
	public List<OnlineStarWork> getWorkList(long osId, int typeId){
//		OnlineStarWorkExample example = new OnlineStarWorkExample();
//		OnlineStarWorkExample.Criteria criteria = example.createCriteria();
//		criteria.andOsIdEqualTo(osId);
//		if(typeId > 0)
//			criteria.andTypeIdEqualTo(typeId);
		return workMapper.selectWorksByOsId(osId, typeId);
	}
	
	public int save(OnlineStar os){
		int affect = osMapper.insert(os);
		addLabels(os);
		return affect;
	}
	
	public int update(OnlineStar os){
		addLabels(os);
		return osMapper.updateByPrimaryKeySelective(os);
	}
	
	public void addLabels(OnlineStar os){
		if(os.getLabelList() != null){
			for(OnlineStarLabel label: os.getLabelList()){
				label.setOsId(os.getId());
				List<OnlineStarLabel> list = osMapper.selectLabelByName(label);
				if(list.size() > 0){
					LOG.info("Exist label, {}, {}", os.getId(), label.getName());
				}
				else{
					osMapper.insertLabel(label);
				}
			}
		}
	}
	
	public void addLabels(List<OnlineStarLabel> labelList){
		if(labelList != null){
			for(OnlineStarLabel label: labelList){
				osMapper.insertLabel(label);
			}
		}
	}
	
	public OnlineStar getByAuthId(long authId){
		OnlineStarExample example = new OnlineStarExample();
		example.createCriteria().andAuthIdEqualTo(authId);
		List<OnlineStar> list = osMapper.selectByExample(example);
		OnlineStar os = list.size() > 0 ? list.get(0) : null;
		if(os != null){
			os.setLabelList(osMapper.selectLabelByOsId(os.getId()));
		}
		return os;
	}
	
	public OnlineStar getByOsId(long osId){
		OnlineStarExample example = new OnlineStarExample();
		example.createCriteria().andIdEqualTo(osId);
		List<OnlineStar> list = osMapper.selectByExample(example);
		OnlineStar os = list.size() > 0 ? list.get(0) : null;
		if(os != null){
			os.setLabelList(osMapper.selectLabelByOsId(os.getId()));
		}
		return os;
	}
	
	public List<JobRecommendOnlineStar> getRecommendList(
			@Param("startRow")int startRow, 
			@Param("cityId")int cityId){
		return osMapper.selectRecommend(0, Constant.PAGE_SIZE, cityId);
	}
	
	public List<OnlineStarRecommend> popularList(){
		return osMapper.selectPopular();
	}
	
	public List<OnlineStarRecommend> freshList(){
		return osMapper.selectFresh();
	}
	
	public List<OnlineStarWork> popularWorkList(){
		return workMapper.selectPopularWorks();
	}
	
	public List<OnlineStarWork> freshWorkList(){
		return workMapper.selectFreshWorks();
	}
}
