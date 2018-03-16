package com.redworld.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TestListener implements ServletContextListener {

	
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Thread ClassLoader: " + Thread.currentThread().getContextClassLoader());
		System.out.println(getClass().getClassLoader());
	}

	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
