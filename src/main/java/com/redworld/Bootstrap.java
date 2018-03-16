package com.redworld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.redworld.mapper.JobMapper;


public class Bootstrap {

	public static void main(String[] args) {
//		@SuppressWarnings({ "unused", "resource" })
		ApplicationContext context = new AnnotationConfigApplicationContext(CaicaiConfig.class);
		
		JobMapper externalMapper = context.getBean(JobMapper.class);
	
		System.out.println(externalMapper);
	}

}
