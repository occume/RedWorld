package com.redworld.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.redworld.Env;

public class EnvironmentListener implements ServletContextListener {
    
    public void contextInitialized(ServletContextEvent sce) {
    	
        String env = Env.get();
        
        System.setProperty("spring.profiles.active", env);
         
        System.out.println("============================");
        System.out.println("Environment: " + Env.get());
        System.out.println("============================");
    }
 
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }
 
}