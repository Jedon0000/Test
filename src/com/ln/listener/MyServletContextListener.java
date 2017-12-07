package com.ln.listener;

import java.util.HashMap;
import java.util.Map;

/**
 * 监听servletcontext对象创建和销毁
 * @author 南天
 */
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;

import com.ln.entity.User;

@WebListener
public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		Map<User, HttpSession> userMap = new HashMap<User, HttpSession>();
		sce.getServletContext().setAttribute("userMap", userMap);
	}
}

