package com.redworld.conroller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redworld.auth.model.Account;
import com.redworld.auth.model.Auth;
import com.redworld.model.Merchant;
import com.redworld.model.Result;
import com.redworld.model.Wallet;
import com.redworld.model.WalletBankcard;
import com.redworld.service.ApplyService;
import com.redworld.service.CommonService;
import com.redworld.service.JobService;
import com.redworld.service.OnlineStarService;
import com.redworld.service.WalletService;

@Controller
@RequestMapping("/wallet/v1")
public class WalletController extends BaseController{
	
	private static final Logger LOG = LoggerFactory.getLogger(WalletController.class);
	
	@Autowired
	private OnlineStarService osService;
	@Autowired
	private ApplyService applyService;
	@Autowired
	private WalletService walletService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private JobService jobService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
    public Object wallet(HttpServletRequest request){
		Account account = checkAndGetAuth(request);
		Wallet wallet = walletService.getByAuthId(account.getAuthId());
    	return Result.ok(wallet);
	}
	
	@RequestMapping(value = "/password/set", method = RequestMethod.POST)
	@ResponseBody
    public Object walletPasswordSet(HttpServletRequest request, @RequestBody Map<String, String> map){
		Account account = checkAndGetAuth(request);
		Wallet wallet = walletService.getByAuthId(account.getAuthId());
		String password = map.get("password");
		wallet.setPassword(password);
		walletService.setPassword(wallet);
    	return Result.ok(wallet);
	}
	
	@RequestMapping(value = "/password/reset", method = RequestMethod.POST)
	@ResponseBody
    public Object walletPasswordReset(HttpServletRequest request, @RequestBody Map<String, String> map){
		Account account = checkAndGetAuth(request);
		Wallet wallet = walletService.getByAuthId(account.getAuthId());
		String password = map.get("password");
		String oldPassword = map.get("old_password");
		if(oldPassword.equalsIgnoreCase(wallet.getPassword())){
			wallet.setPassword(password);
			walletService.setPassword(wallet);
		}
		else{
			return Result.fail("Password incorrect");
		}
		
    	return Result.ok(wallet);
	}
	
	@RequestMapping(value = "/bankcard/add", method = RequestMethod.POST)
	@ResponseBody
    public Object walletBankcardAdd(HttpServletRequest request, @RequestBody WalletBankcard bankcard){
		Account account = checkAndGetAuth(request);
		bankcard.setAuthId(account.getAuthId());
		walletService.addBankcard(bankcard);
    	return Result.OK;
	}
	
	@RequestMapping(value = "/bankcard/list", method = RequestMethod.GET)
	@ResponseBody
    public Object walletBankcardList(HttpServletRequest request){
		Account account = checkAndGetAuth(request);
    	return Result.ok(walletService.getBankcardList(account.getAuthId()));
	}
}