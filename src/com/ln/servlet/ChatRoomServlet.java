package com.ln.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ln.entity.User;
import com.ln.service.UserService;
import com.ln.servlet.base.BaseServlet;

/**
 * 实现所有的servlet
 * 
 */
@WebServlet("/chat")
public class ChatRoomServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	static Logger log = Logger.getLogger(ChatRoomServlet.class);

	/**
	 * 处理登录的功能
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("处理ChatRoomServlet的login方法");
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
			return null;
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
			return null;		
		}								
	}
	/**
	 * 处理踢人的功能
	 */
	public String kick(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.拿到想踢的人的id
		int id = Integer.parseInt(request.getParameter("id"));
		//2.拿到所有在线的人的列表
		Map<User, HttpSession> userMap = 
				(Map<User, HttpSession>) getServletContext().getAttribute("userMap");
		//3.通过id找到这个人的在Map中对应的session
		User user = new User();
		user.setId(id);
		HttpSession session = userMap.get(user);
		//4.销毁他的session
		session.invalidate();
		//5.重定向到main.jsp
		response.sendRedirect("main.jsp");
		return null;
	}
	
	/**
	 * 退出聊天室
	 */
	public String exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取session对象
		HttpSession session = request.getSession();
		// 将session销毁
		session.invalidate();
		log.info("req.getContextPath() : " + request.getContextPath());
		// 到首页
		response.sendRedirect(request.getContextPath() + "/index.jsp");
		return null;
	}
	/**
	 * 检查session是否过期
	 * 
	 */
	public String check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 从session中获取用户的信息
		User exitUser = (User) request.getSession().getAttribute("exitUser");
		// 判断session中的用户是否过期
		if (exitUser == null) {
			// 登录信息已经过期
			response.getWriter().println("1");
		} else {
			// 登录信息没有过期
			response.getWriter().println("2");
		}
		return null;
	}	
	/**
	 * 发送聊天内容
	 */
	public String sendMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String face = request.getParameter("face");
		String color = request.getParameter("color");
		String content = request.getParameter("content");		
		//发言的时间 
		Date date = new Date();
		SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = fomat.format(date);
		//获取servletcontext对象
		ServletContext application = getServletContext();
		
		//聊天发言内容
		String sourceMsg = "";
		//获取聊天内容
		sourceMsg = (String)application.getAttribute("message");
		
		sourceMsg += "<strong>" + from + "</strong><font color='#CC0000'>" + 
		face + "</font>对[" + to+ "] 说：<font color='"+color+"'>" + content + "("+ time +")<br/>";
		
		//将消息存入servletcontext/application的范围
		application.setAttribute("message", sourceMsg);
		return showMsg(request, response);
	}
	
	/**
	 * 获取聊天的内容
	 */
	public String showMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = (String) this.getServletContext().getAttribute("message");
		if(message != null){
			response.getWriter().println(message);
		}
		return null;
	}
}
