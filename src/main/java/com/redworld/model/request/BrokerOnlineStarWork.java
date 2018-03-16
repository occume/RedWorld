package com.redworld.model.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.redworld.model.OnlineStarWork;

public class BrokerOnlineStarWork {

	@JsonProperty("os_id")
	public long osId;
	
	@JsonProperty("work_list")
	public List<OnlineStarWork> workList; 
}
