package store.service.impl;

import java.sql.SQLException;
import java.util.List;

import redis.clients.jedis.Jedis;
import store.Bean.Category;
import store.dao.CategoryDao;
import store.dao.impl.CategoryDaoImpl;
import store.service.CategoryService;
import store.utils.JedisUtils;

public class CategoryServiceImpl implements CategoryService{
	CategoryDao categoryDao=new CategoryDaoImpl();
	
	@Override
	public List<Category> getAllCats() throws SQLException {
		return categoryDao.getAllCats();
		
	}

	@Override
	public void addCategory(Category c) throws SQLException {
		categoryDao.addCategory(c);
		//更新redis缓存
		Jedis jedis=JedisUtils.getJedis();
		jedis.del("allCats");
		JedisUtils.closeJedis(jedis);
		
	}

	@Override
	public Category findCatsByCid(String cid) throws SQLException {
		return categoryDao.findCatsByCid(cid);
	}

	@Override
	public void updateCategory(Category c) throws SQLException {
		categoryDao.updateCategory(c);
		
	}

	

}
