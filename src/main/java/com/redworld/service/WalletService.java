package com.redworld.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redworld.mapper.OnlineStarMapper;
import com.redworld.mapper.WalletBankcardMapper;
import com.redworld.mapper.WalletMapper;
import com.redworld.model.OnlineStar;
import com.redworld.model.OnlineStarExample;
import com.redworld.model.Wallet;
import com.redworld.model.WalletBankcard;
import com.redworld.model.WalletBankcardExample;
import com.redworld.model.WalletExample;

@Service
public class WalletService {

	@Autowired
	private WalletMapper walletMapper;
	@Autowired
	private WalletBankcardMapper bankcardMapper;
	
	public int insert(Wallet wallet){
		return walletMapper.insert(wallet);
	}
	
	public int update(Wallet wallet){
		return walletMapper.updateByPrimaryKeySelective(wallet);
	}
	
	public Wallet getByAuthId(long authId){
		WalletExample example = new WalletExample();
		example.createCriteria().andAuthIdEqualTo(authId);
		return walletMapper.selectByExample(example).get(0);
	}
	
	public void setPassword(Wallet wallet){
		walletMapper.updateByPrimaryKeySelective(wallet);
	}
	
	public List<WalletBankcard> getBankcardList(long authId){
		WalletBankcardExample example = new WalletBankcardExample();
		WalletBankcardExample.Criteria c = example.createCriteria();
		c.andauthIdEqualTo(authId);
		return bankcardMapper.selectByExample(example);
	}
	
	public int addBankcard(WalletBankcard bankcard){
		return bankcardMapper.insert(bankcard);
	}
}
