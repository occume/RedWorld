package com.redworld.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redworld.mapper.BrokerMapper;
import com.redworld.mapper.OnlineStarMapper;
import com.redworld.mapper.OnlineStarWorkMapper;
import com.redworld.model.Broker;
import com.redworld.model.BrokerExample;
import com.redworld.model.BrokerOnlineStarResponse;
import com.redworld.model.OnlineStar;
import com.redworld.model.OnlineStarWork;

@Service
public class BrokerService {

	@Autowired
	private BrokerMapper brokerMapper;
	@Autowired
	private OnlineStarWorkMapper onlineStarWorkMapper;
	
	public int update(Broker broker){
		return brokerMapper.updateByPrimaryKeySelective(broker);
	}
	
	public int insert(Broker broker){
		return brokerMapper.insert(broker);
	}
	
	public List<BrokerOnlineStarResponse> onlineStarList(long brokerId){
		return brokerMapper.selectAccount(brokerId);
	}
	
	public int addOnlineStar(long brokerId, long osId){
		return brokerMapper.addOnlineStar(brokerId, osId);
	}
	
	public Broker getByAuthId(long authId){
		BrokerExample example = new BrokerExample();
		example.createCriteria().andAuthIdEqualTo(authId);
		List<Broker> list = brokerMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<OnlineStarWork> getWorkList(long brokerId, int typeId) {
		List<Long> osIdList = brokerMapper.selectOsIdList(brokerId);
		System.out.println(osIdList);
		return onlineStarWorkMapper.selectWorksOfBroker(osIdList, typeId);
	}
}
