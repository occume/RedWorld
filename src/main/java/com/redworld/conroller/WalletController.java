package com.redworld.conroller;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redworld.SendMan;
import com.redworld.Constant.SessionKey;
import com.redworld.auth.model.Account;
import com.redworld.auth.model.Auth;
import com.redworld.model.Merchant;
import com.redworld.model.Result;
import com.redworld.model.Wallet;
import com.redworld.model.WalletBankcard;
import com.redworld.model.response.WalletBankcardResponse;
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
	
	@RequestMapping(value = "/v-code", method = RequestMethod.GET)
	@ResponseBody
    public Object walletVcode(HttpSession session, HttpServletRequest request){
		Account account = checkAndGetAuth(request);
		int code = genVcode();
		session.setAttribute(SessionKey.VCODE, code);
//		String phone = map.get("phone");
//		SendMan.sms(account.get, code);
    	return Result.OK;
	}
	
	private int genVcode() {
		int start = 100000;
		int end = 999999;
		return start + new Random().nextInt((end - start) + 1);
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
		System.out.println(bankcard.getCardTypeId());
		System.out.println(bankcard.getCardNumber());
		bankcard.setAuthId(account.getAuthId());
		List<WalletBankcardResponse> list = walletService.getBankcardList(bankcard);
		System.out.println(list);
		if(list.size() > 0){
			return Result.fail("不能重复添加噢");
		}
		
		walletService.addBankcard(bankcard);
    	return Result.OK;
	}
	
	@RequestMapping(value = "/bankcard/list", method = RequestMethod.GET)
	@ResponseBody
    public Object walletBankcardList(HttpServletRequest request){
		Account account = checkAndGetAuth(request);
//    	return Result.ok(walletService.getBankcardList(account.getAuthId()));
		WalletBankcard bankCard = new WalletBankcard();
		bankCard.setAuthId(account.getAuthId());;
		return Result.ok(walletService.getBankcardList(bankCard));
	}
	
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	@ResponseBody
    public Object withdraw(HttpServletRequest request, @RequestBody Map<String, String> map){
		Account account = checkAndGetAuth(request);
		String password = map.get("password");
    	return Result.ok(walletService.getBankcardList(account.getAuthId()));
	}
}