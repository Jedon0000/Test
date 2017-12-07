package com.ln.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ln.entity.User;

/**
 * URL session 安全验证的filter
 */
public class LoginFilter implements Filter {

	static Logger log = Logger.getLogger(LoginFilter.class);
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		User user = (User)httpRequest.getSession().getAttribute("user");
		if(user != null){
			log.debug("用户已登录");
			chain.doFilter(httpRequest, httpResponse);
		}else{
			log.debug("请登录账号");
			httpResponse.sendRedirect("/UserLogin/index.html");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
}
