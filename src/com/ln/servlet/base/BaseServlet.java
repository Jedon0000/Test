package com.ln.servlet.base;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ln.servlet.UserServlet;

/**
 * servlet的基类
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(BaseServlet.class);	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");		
		//http://localhost:8080/chatroom/XXXX?method=login
		String methodName = request.getParameter("method"); //它是一个方法的名称
		log.info("method : " + methodName);
		
		//当没用指定要调用的方法时，那么默认的请求是execute()方法
		if(methodName == null || methodName.isEmpty()){
			methodName = "execute";
		}	
		Class c = this.getClass();		
		try {
			//通过方法的名称获取方法的反射对象
			Method m = c.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			//反射方法的目标方法，也就是说，假设methodName为add，那么就调用add方法
			String result = (String) m.invoke(this, request, response);
			
			if(result != null && !result.isEmpty()){
				request.getRequestDispatcher(result).forward(request, response);
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}	
}
