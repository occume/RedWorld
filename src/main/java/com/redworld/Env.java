package com.redworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Env {

	public static final String PROD 		= "prod";
	public static final String TEST 		= "test";
	public static final String DEV 			= "dev";
	
    private static String env = DEV;

    static{
        try {
            byte[] bytes = Files.readAllBytes(Paths.get("/data/env"));
            env = new String(bytes);
            env = env.replaceAll("\\s", "");
        }catch(Exception e){
            
        }
    }

    public static String get(){
        return env;
    }
}
