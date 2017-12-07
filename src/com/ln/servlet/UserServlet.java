package com.ln.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ln.entity.User;
import com.ln.service.UserService;

/**
 * 处理用户登录请求的Servlet
 */

//@WebServlet("/userlogin")
public class UserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;	
	
	static Logger log = Logger.getLogger(UserServlet.class);
	/**
	 * 处理登录的post请求
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String username = request.getParameter("username");
		String userpwd = request.getParameter("password");
		
		User user = new User();
		user.setUserName(username);
		user.setPassword(userpwd);
		
		UserService userService = new UserService();
		User exitUser = userService.login(user);
		if(exitUser == null){
			request.setAttribute("msg", "用户或密码错误");			
			//转发
			request.getRequestDispatcher("index.jsp").forward(request, response);						
		}else{						
			request.getSession().invalidate();
			//获取servletcontext中存在map集合
			Map<User, HttpSession> userMap =(Map<User, HttpSession>) getServletContext().getAttribute("userMap");			
			//判断当前用户是否已经存在map集合中
			if(userMap.containsKey(exitUser)){
				//说明map对象中有这个用户 
				HttpSession httpsession = userMap.get(exitUser);
				//将这个session销毁
				httpsession.invalidate();
			}
			//使用监听器： HttpSessionBindingListener作用在User JavaBean上的监听器
			request.getSession().setAttribute("exitUser", exitUser);			
			log.info("session " + ((User)request.getSession().getAttribute("exitUser")).getUserName());			
			
			//聊天的信息集合
			String sourceMsg = "";	
			ServletContext application = getServletContext();
			if(application.getAttribute("message") != null){
				sourceMsg = application.getAttribute("message").toString();
			}
			sourceMsg += "系统公告：<font color='gray'>" + exitUser.getUserName() + "走进聊天室</font><br/>";
			
			application.setAttribute("message", sourceMsg);
			
			//重定向
			response.sendRedirect(request.getContextPath() +"/main.jsp");	
		}								
	}
}
