package store.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import store.Bean.User;
import store.dao.UserDao;
import store.utils.JDBCUtils;

public class UserDaoImpl implements UserDao{

	@Override
	public void userRegist(User user) throws SQLException {
		String sql="insert into user values(?,?,?,?,?,?,?,?,?,?)";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={user.getUid(),
						 user.getUsername(),
						 user.getPassword(),
						 user.getName(),
						 user.getEmail(),
						 user.getTelephone(),
						 user.getBirthday(),
						 user.getSex(),
						 user.getState(),
						 user.getCode()
					};
		qr.update(sql, params);
	}

	
	
	@Override
	public User userLogin(User user) throws SQLException{
		String sql="select * from user where username=? and password=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
	}

}




































































































































