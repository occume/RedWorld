package com.redworld;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.redworld.mapper.JobMapper;
import com.redworld.model.OnlineStar;
import com.redworld.service.OnlineStarService;

public class MybatisTest {
	
	public static ApplicationContext ctx;
	public static OnlineStarService oss;
	
	static{
		ctx = new ClassPathXmlApplicationContext("/beans.xml");
		oss = ctx.getBean(OnlineStarService.class);
	}
	
	public static void main(String...strings){
		JobMapper jobMapper = ctx.getBean(JobMapper.class);
		System.out.println(jobMapper);
//		OrderService orderService = ctx.getBean(OrderService.class);
//		NarraterService ns = ctx.getBean(NarraterService.class);
//		System.out.println(ns.getAll().get(0).getYoukuUrl());
		saveFollow();
//		getNarratersByFollow();
	}
	
	public static void saveFollow(){
//		OnlineStar os = new OnlineStar("jd", "13333333333", "530050582", "HuaJiao");
//		oss.save(os);
	}
	
}
