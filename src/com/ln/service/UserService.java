package com.ln.service;

import com.ln.dao.UserDao;
import com.ln.dao.impl.UserDaoImpl;
import com.ln.entity.User;

/**
 * 业务层
 */
public class UserService {

	public User login(User user){
		UserDao dao = new UserDaoImpl();
		return dao.login(user);
	}
}
