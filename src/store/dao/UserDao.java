package store.dao;

import java.sql.SQLException;

import store.Bean.User;

public interface UserDao {

	void userRegist(User user)throws SQLException;

	User userLogin(User user)throws SQLException;

}
