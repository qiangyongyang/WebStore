package store.service;

import java.sql.SQLException;

import store.Bean.User;

public interface UserService {

	void userRegist(User user)throws SQLException;

	User userLogin(User user)throws SQLException;

}
