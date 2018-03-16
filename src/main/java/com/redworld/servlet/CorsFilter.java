package com.redworld.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter {
	
	private String allowHeaders = "Duliday-Agent, Duliday-Agent-Version, Duliday-Device-Code,"
			+ " Duliday-Device-Id, Duliday-OS-Version, Content-Type, Accept, Accept-Encoding, Cookie, x-requested-with,Authorization";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String origin = (String) req.getRemoteHost() + ":" + req.getRemotePort();
		String method = req.getMethod();
		origin = req.getHeader("Origin");
		
		resp.setHeader("Access-Control-Allow-Origin", origin);
		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		resp.setHeader("Access-Control-Max-Age", "3600");
		resp.setHeader("Access-Control-Allow-Headers", allowHeaders);
		resp.setHeader("Access-Control-Allow-Credentials","true");
		System.out.println("Cors filter");
		if("OPTIONS".equals(method)){
			resp.setStatus(204);
			resp.getWriter().println();
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		
	}

}
