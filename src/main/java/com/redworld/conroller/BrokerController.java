package com.redworld.conroller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redworld.Constant;
import com.redworld.auth.model.Account;
import com.redworld.auth.model.Auth;
import com.redworld.auth.service.AccountService;
import com.redworld.model.Apply;
import com.redworld.model.ApplyWithOnlineStar;
import com.redworld.model.Broker;
import com.redworld.model.BrokerOnlineStar;
import com.redworld.model.Job;
import com.redworld.model.Merchant;
import com.redworld.model.OnlineStar;
import com.redworld.model.OnlineStarWork;
import com.redworld.model.Result;
import com.redworld.model.request.BrokerOnlineStarWork;
import com.redworld.service.ApplyService;
import com.redworld.service.BrokerService;
import com.redworld.service.JobService;
import com.redworld.service.MerchantService;
import com.redworld.service.OnlineStarService;

@Controller
@RequestMapping("/broker/v1")
public class BrokerController extends BaseController{
	
	private static final Logger LOG = LoggerFactory.getLogger(BrokerController.class);
	
	@Autowired
	private AccountService accService;
	@Autowired
	private OnlineStarService osService;
	@Autowired
	private BrokerService brokerService;
	@Autowired
	private JobService jobService;
	@Autowired
	private ApplyService applyService;
	
	private Broker getProfile(Account account) {
		return brokerService.getByAuthId(account.getAuthId());
	}
	
	@RequestMapping(value = "/profile/{authId}", method = RequestMethod.POST)
	@ResponseBody
    public Object profile(HttpServletRequest request, @PathVariable long authId){
		Broker broker;
//		long authId = getParamLong("auth_id", map);
		if(authId == 0){
			Account account = checkAndGetAuth(request);
			broker = brokerService.getByAuthId(account.getAuthId());
		}
		else{
			broker = brokerService.getByAuthId(authId);
		}
		 
    	return Result.ok(broker);
	}
	
	@RequestMapping(value = "/profile/update", method = RequestMethod.POST)
	@ResponseBody
    public Object profileUpdate(HttpServletRequest request, @Valid @RequestBody Broker broker){
		checkAndGetAuth(request);
		brokerService.update(broker);
    	return Result.OK;
	}
	
	@RequestMapping(value = "/os/add", method = RequestMethod.POST)
	@ResponseBody
    public Object onlineStarAdd(HttpServletRequest request, @RequestBody BrokerOnlineStar bos){
		Account account = checkAndGetAuth(request);
		Broker broker = getProfile(account);
		if(broker == null) return Result.fail("Group Broker is not selected");
		
		Account acc = bos.account();
		accService.save(acc);
		System.out.println(acc.getId());
		
		OnlineStar os = bos.onlineStar();
		os.setAuthId(0l);
		os.setAccountId(acc.getId());
		os.setBrokerId(broker.getId());
		osService.save(os);

		brokerService.addOnlineStar(broker.getId(), os.getId());
		
    	return Result.OK;
	}
	
	@RequestMapping(value = "/os/list", method = RequestMethod.POST)
	@ResponseBody
    public Object onlineStarList(HttpServletRequest request, @RequestBody Map<String, Object> map){
		Account account = checkAndGetAuth(request);
		Broker broker = getProfile(account);
		if(broker == null) return Result.fail("Group Broker is not selected");
		
		int page = getParamInt("page", map);
		
    	return Result.ok(brokerService.onlineStarList(broker.getId()));
	}
	
	/**
	 * 上传作品
	 * @param request
	 * @param workList
	 * @return
	 */
	@RequestMapping(value = "/os/work/add", method = RequestMethod.POST)
	@ResponseBody
    public Object workAdd(HttpServletRequest request, @RequestBody BrokerOnlineStarWork bos){
		Account account = checkAndGetAuth(request);
		
//		System.out.println(auth.getId());
		long osId = bos.osId;
		List<OnlineStarWork> workList = bos.workList;
//		if(os == null) return Result.fail("Group is not selected");
		for(OnlineStarWork work: workList){
			work.setOsId(osId);
			osService.addWork(work);
		}
    	return Result.OK;
	}
	
	@RequestMapping(value = "/os/work/list", method = RequestMethod.POST)
	@ResponseBody
    public Object workList(HttpServletRequest request, @RequestBody Map<String, Object> map){
		Account account = checkAndGetAuth(request);
		Broker broker = getProfile(account);
		if(broker == null) Result.fail("Group is not selected");
		
		int typeId = getParamInt("type_id", map);
		int page = getParamInt("page", map);
		List<OnlineStarWork> data = brokerService.getWorkList(broker.getId(), typeId);
		
    	return Result.ok(data);
	}
}