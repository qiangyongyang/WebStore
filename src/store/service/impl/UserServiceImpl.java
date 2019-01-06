package store.service.impl;

import java.sql.SQLException;

import store.Bean.User;
import store.dao.UserDao;
import store.dao.impl.UserDaoImpl;
import store.service.UserService;

public class UserServiceImpl implements UserService{

	@Override
	public void userRegist(User user) throws SQLException {
		UserDao userDao=new UserDaoImpl();
		userDao.userRegist(user);
	}

	@Override
	public User userLogin(User user) throws SQLException{
		UserDao userDao=new UserDaoImpl();
		User uu=userDao.userLogin(user);
		if(user==null){
			throw new RuntimeException("用户名或密码有误");
		}else{
			return uu;
		}
		
	}

}
