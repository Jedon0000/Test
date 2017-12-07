package com.ln.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ln.entity.User;

/**
 * 踢人的servlet
 */
//@WebServlet("/kick")
public class KickServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
