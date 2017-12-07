package com.ln.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * 退出聊天室的servlet
 */
//@WebServlet("/exit")
public class ExitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger log = Logger.getLogger(ExitServlet.class);

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// 获取session对象
		HttpSession session = req.getSession();
		// 将session销毁
		session.invalidate();
		log.info("req.getContextPath() : " + req.getContextPath());
		// 到首页
		resp.sendRedirect(req.getContextPath() + "/index.jsp");
	}
}
