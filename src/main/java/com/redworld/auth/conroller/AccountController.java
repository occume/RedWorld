package com.redworld.auth.conroller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redworld.Constant.GroupType;
import com.redworld.Constant.HeaderName;
import com.redworld.auth.model.Account;
import com.redworld.auth.model.Auth;
import com.redworld.auth.service.AccountService;
import com.redworld.auth.service.AuthService;
import com.redworld.conroller.BaseController;
import com.redworld.model.AdvertCompany;
import com.redworld.model.Broker;
import com.redworld.model.Merchant;
import com.redworld.model.OnlineStar;
import com.redworld.model.Result;
import com.redworld.model.Wallet;
import com.redworld.service.AdvertCompanyService;
import com.redworld.service.BrokerService;
import com.redworld.service.CommonService;
import com.redworld.service.MerchantService;
import com.redworld.service.OnlineStarService;
import com.redworld.service.WalletService;

@Controller
@RequestMapping("/passport/v1")
public class AccountController extends BaseController{
	
	private static final String JSON = "application/json;charset=UTF-8";
	private static final String TEXT = "application/json;charset=UTF-8";
	
	private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private CommonService commonService;
	@Autowired
	private AuthService authService;
	@Autowired
	private AccountService accService;
	@Autowired
	private OnlineStarService osService;
	@Autowired
	private WalletService walletService;
	@Autowired
	private MerchantService merchantService;
	@Autowired
	private BrokerService brokerService;
	@Autowired
	private AdvertCompanyService advertService;
	
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	@ResponseBody
    public Object info(HttpServletRequest requet, @RequestBody Map<String, Object> map){
		long authId = getParamLong("auth_id", map);
		if(authId == 0){
			Account account   = checkAndGetAuth(requet);
			authId = account.getAuthId();
		}
		
		Account acc = accService.getByAuthId(authId);
    	return Result.ok(acc);
    }
	
	@RequestMapping(value = "/profile/complete", method = RequestMethod.POST)
	@ResponseBody
    public Object profileUpdate(HttpServletRequest request, @Valid @RequestBody Account acc){
		Account account   = checkAndGetAuth(request);
//		Account account = accService.getByAuthId(auth.getId());
		acc.setAuthId(account.getAuthId());
//		System.out.println(auth.getId());
		System.out.println(account);
//		if(account == null){
//			accService.save(acc);
//		}
//		else{
			accService.update(acc);
//		}
    	return Result.OK;
	}
	
	@RequestMapping(value = "/registionid/set", method = RequestMethod.POST)
	@ResponseBody
    public Object registionidSet(HttpServletRequest request, @RequestBody Map<String, String> map){
		Account account = checkAndGetAuth(request);
		String registionid = map.get("registionid");
		accService.setRegistionid(account.getAuthId(), registionid);
		
		String token = request.getHeader(HeaderName.TOKEN);
		account.setRegistionid(registionid);
		request.getSession().setAttribute(token, account);
    	return Result.OK;
	}
	
	@RequestMapping(value = "/group/select", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
    public Object selectGroup(HttpServletRequest request, @RequestBody Map<String, Integer> map){
		Account account = checkAndGetAuth(request);
//		Account account = accService.getByAuthId(auth.getId());
		
		if(account == null){
			return Result.fail("Profile not completed");
		}
		
		if(account.getGroupId() > 0){
			return Result.fail("Group selected");
		}
		int groupId = map.get("group_id");
		accService.selectGroup(account.getAuthId(), groupId);
		if(groupId == GroupType.ONLINE_STAR){
			/**
			 * Create OnlineStar
			 */
			OnlineStar os = new OnlineStar();
			os.setAuthId(account.getAuthId());
			os.setAccountId(account.getId());
			OnlineStar dbOS = osService.getByAuthId(account.getAuthId());
			if(dbOS == null){
				osService.save(os);
				/**
				 * Create Wallet
				 */
				Wallet wallet = new Wallet();
				wallet.setAuthId(account.getAuthId());
				walletService.insert(wallet);
			}
		}
		else if(groupId == GroupType.MERCHANT){
			/**
			 * Create Merchant
			 */
			Merchant merchant = new Merchant();
			merchant.setAuthId(account.getAuthId());
			merchantService.insert(merchant);
		}
		else if(groupId == GroupType.BROKER_COMPANY){
			/**
			 * Create Broker
			 */
			Broker broker = new Broker();
			broker.setAuthId(account.getAuthId());
			brokerService.insert(broker);
		}
		else if(groupId == GroupType.ADV_COMPANY){
			/**
			 * Create AdvertCompany
			 */
			AdvertCompany advert = new AdvertCompany();
			advert.setAuthId(account.getAuthId());
			advertService.insert(advert);
			
			/**
			 * Create Wallet
			 */
			Wallet wallet = new Wallet();
			wallet.setAuthId(account.getAuthId());
			walletService.insert(wallet);
		}
    	return Result.OK;
	}
}