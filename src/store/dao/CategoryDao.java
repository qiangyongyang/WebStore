package store.dao;

import java.sql.SQLException;
import java.util.List;

import store.Bean.Category;

public interface CategoryDao {

	List<Category> getAllCats()throws SQLException;

	void addCategory(Category c)throws SQLException;

	Category findCatsByCid(String cid)throws SQLException;

	void updateCategory(Category c)throws SQLException;

}
