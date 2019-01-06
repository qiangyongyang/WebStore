package store.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import store.Bean.Category;
import store.dao.CategoryDao;
import store.utils.JDBCUtils;

public class CategoryDaoImpl implements CategoryDao{
	QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
	
	@Override
	public List<Category> getAllCats() throws SQLException {
		String sql="select *from category";
		return qr.query(sql, new BeanListHandler<Category>(Category.class));
		
	}

	
	
	@Override
	public void addCategory(Category c) throws SQLException {
		String sql="insert into category values (? ,?)";
		qr.update(sql,c.getCid(),c.getCname());
	}



	@Override
	public Category findCatsByCid(String cid) throws SQLException {
		String sql="select * from category where cid=?";
		return qr.query(sql, new BeanHandler<Category>(Category.class), cid);
	}



	@Override
	public void updateCategory(Category c) throws SQLException {
		String sql="update category set cname=? where cid=?";
		qr.update(sql, c.getCname(),c.getCid());
	}



		
	



	

}
