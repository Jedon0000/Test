package com.ln.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.apache.log4j.Logger;

/**
 * 字符编码的过滤器
 */
public class EncodingFilter implements Filter{
	
	static Logger log = Logger.getLogger(EncodingFilter.class);
	
	private String encoding;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding(encoding);	
		response.setCharacterEncoding(encoding);		
		response.setContentType("text/html;charset=utf-8");		
		log.debug("请求进入了字符编码的Filter");
		chain.doFilter(request, response);		
	}
	
	@Override
	public void init(FilterConfig fconfig) throws ServletException {
		// TODO Auto-generated method stub
		encoding = fconfig.getInitParameter("encoding");
		log.info("字符编码"+encoding);
	}
}
