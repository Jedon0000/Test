package com.ln.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

//@WebServlet("/sendMsg")
public class SendMsgServlet extends HttpServlet {
	
	static Logger log = Logger.getLogger(SendMsgServlet.class);
	
	private static final long serialVersionUID = 1L;
       	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.info("发送信息");
		
		String from = req.getParameter("from");
		String to = req.getParameter("to");
		String face = req.getParameter("face");
		String color = req.getParameter("color");
		String content = req.getParameter("content");
		
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
		if(sourceMsg != ""){
			resp.getWriter().println(sourceMsg);
		}
	}
}
