package com.ln.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ln.dao.UserDao;
import com.ln.entity.User;
import com.ln.jdbc.utils.RowMapper;
import com.ln.jdbc.utils.JdbcTemplate;

/**
 * DAO实现类
 */
public class UserDaoImpl extends JdbcTemplate implements UserDao {

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		String sql = "select * from user where username=? and password=?";
		Object[] params = new Object[]{user.getUserName(), user.getPassword()};
		List<User> list = this.queryForList(new RowMapper<User>(){

			@Override
			public User mappingRow(ResultSet rs, int rownum) throws SQLException {
				// TODO Auto-generated method stub
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setType(rs.getString("type"));
				return user;
			}			
		}, sql, params);
		
		if(list != null && list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
	}
}
