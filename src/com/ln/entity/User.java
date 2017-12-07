package com.ln.entity;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.log4j.Logger;

public class User implements HttpSessionBindingListener{
	
	static Logger log = Logger.getLogger(User.class);

	private int id;	
	private String userName;	
	private String password;	
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this == obj)
			return true;
					
		if(obj == null)
			return false;
				
		if(getClass() != obj.getClass())
			return false;
							
		User other = (User) obj;
		
		if(id != other.id){
			return false;
		}else{
			return true;
		}	
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int result =1;
		result = result*31 + id;
		return result;
	}
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		log.info("进入了User对象的绑定");
		HttpSession session = event.getSession();
		log.info("===" + session.getId());
		//获得人员列表
		Map<User, HttpSession> userMap = 
				(Map<User, HttpSession>) session.getServletContext().getAttribute("userMap");
		//将用户 放入Map对象
		userMap.put(this, session);
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		log.info("退出，解绑User对象");
		HttpSession session = event.getSession();
		//获得人员列表
		Map<User, HttpSession> userMap = 
				(Map<User, HttpSession>) session.getServletContext().getAttribute("userMap");
		//将用户移除
		userMap.remove(this);
	}
}
