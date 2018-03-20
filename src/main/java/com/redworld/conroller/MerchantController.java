package com.redworld.conroller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redworld.Constant;
import com.redworld.Message1;
import com.redworld.SendMan;
import com.redworld.auth.model.Account;
import com.redworld.model.Apply;
import com.redworld.model.ApplyWithAccount;
import com.redworld.model.ApplyWithOnlineStar;
import com.redworld.model.Job;
import com.redworld.model.JobImage;
import com.redworld.model.Merchant;
import com.redworld.model.Result;
import com.redworld.model.response.JobResponse;
import com.redworld.service.ApplyService;
import com.redworld.service.JobService;
import com.redworld.service.MerchantService;
import com.redworld.service.OnlineStarService;

@Controller
@RequestMapping("/merchant/v1")
public class MerchantController extends BaseController{
	
	private static final Logger LOG = LoggerFactory.getLogger(MerchantController.class);
	
	@Autowired
	private MerchantService merchantService;
	@Autowired
	private JobService jobService;
	@Autowired
	private ApplyService applyService;
	@Autowired
	private OnlineStarService osService;

	private Merchant getProfile(Account account){
		return merchantService.getByAuthId(account.getAuthId());
	}
	
	@RequestMapping(value = "/job/post/os/list", method = RequestMethod.POST)
	@ResponseBody
    public Object jobCreateOsList(HttpServletRequest request, @RequestBody Map<String, Object> map){
		Account account = checkAndGetAuth(request);
//		Merchant merchant = getProfile(account);
//		if(merchant == null) return Result.fail("Group is not selected");
		int cityId = getParamInt("city_id", map);
		int genderId = getParamInt("gender_id", map);
		double price = getParamDouble("price", map);
		int count = getParamInt("expect_count", map);

//		System.out.println(job.getImageList());
//		jobService.insert(job);
    	return Result.ok(osService.getRecommendList(0, cityId));
	}
	
	@RequestMapping(value = "/job/post", method = RequestMethod.POST)
	@ResponseBody
    public Object createJob(HttpServletRequest request, @Valid @RequestBody Job job){
		Account account = checkAndGetAuth(request);
		job.setAuthId(account.getAuthId());
		job.setStatusId(1);
		jobService.insert(job);
		
		if(job.getOsList() != null){
			for(Account acc: job.getOsList()){
				long jobId = job.getId();
				Apply apply = Apply.newApply(jobId, acc.getAuthId());
				if(applyService.exist(apply)){
					LOG.info("Already applied");
				}
				else{
					applyService.create(Apply.newApply(jobId, acc.getAuthId(), 2));
					Message1 message = new Message1(account.getRegistionid(), "[Hongrz] this is alert", 
							"[Hongrz] this is title", 1, account.getAuthId(), "[Hongrz] this is a link");
					SendMan.send(message);
				}
			}
		}
		
		if(job.getImageList() != null){
			for(JobImage image: job.getImageList()){
				image.setJobId(job.getId());
				jobService.insert(image);
			}
		}
//		System.out.println(job.getImageList());
//		jobService.insert(job);
    	return Result.ok(job);
	}
	
	@RequestMapping(value = "/job/list", method = RequestMethod.POST)
	@ResponseBody
    public Object jobList(HttpServletRequest request, @RequestBody Map<String, Object> map){
		Account account = checkAndGetAuth(request);
		Merchant merchant = getProfile(account);
		int page = getParamInt("page", map);
		Result result;
		if(merchant != null){
			LOG.info("{}", map);
			List<JobResponse> data = jobService.jobListOfMerchant(page, map);
			result = Result.ok(data);
		}
		else{
			result = Result.fail("Group is not selected");
		}
    	return result;
	}
	
	@RequestMapping(value = "/job/apply/list", method = RequestMethod.POST)
	@ResponseBody
    public Object jobApplyList(HttpServletRequest request, @RequestBody Map<String, Long> map){
		Account account = checkAndGetAuth(request);
//		Merchant merchant = getProfile(account);
		long jobId = map.get("job_id");
		List<ApplyWithAccount> data = applyService.selectWithAccount(jobId);
		
    	return Result.ok(data);
	}
	
	@RequestMapping(value = "/job/apply/pass", produces = TEXT, method = RequestMethod.POST)
	@ResponseBody
    public Object applyPass(HttpServletRequest request, @RequestBody Map<String, Long> map){
		checkAndGetAuth(request);
		
		long applyId = map.get("apply_id");
		Apply apply = applyService.getById(applyId);
		applyService.handleApply(applyId, Constant.ApplyStatus.RUNNING);
		
    	return Result.OK;
	}
	
	@RequestMapping(value = "/job/apply/refuse", produces = TEXT, method = RequestMethod.POST)
	@ResponseBody
    public Object applyRefuse(HttpServletRequest request, @RequestBody Map<String, Long> map){
		Account account = checkAndGetAuth(request);
		
		long applyId = map.get("apply_id");
		Apply apply = applyService.getById(applyId);
		applyService.handleApply(applyId, Constant.ApplyStatus.NO_PASS);
		
    	return Result.OK;
	}
	
	@RequestMapping(value = "/job/apply/handle", produces = TEXT, method = RequestMethod.POST)
	@ResponseBody
    public Object applyHandle(HttpServletRequest request, @RequestBody Map<String, Object> map){
		checkAndGetAuth(request);
		
		long applyId = getParamLong("apply_id", map);
		int statusId = getParamInt("status_id", map);
//		Apply apply = applyService.getById(applyId);
		applyService.handleApply(applyId, statusId);
		
    	return Result.OK;
	}
}