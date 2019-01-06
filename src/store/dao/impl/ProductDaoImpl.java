package store.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import store.Bean.Product;
import store.dao.ProductDao;
import store.utils.JDBCUtils;

public class ProductDaoImpl implements ProductDao{
	QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
	
	@Override
	public List<Product> findHots() throws Exception{
		String sql="select * from product where pflag=0 and is_hot= 1 order by pdate desc limit 0 , 9";
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public List<Product> findNews() throws Exception {
		String sql="select * from product where pflag=0 order by pdate desc limit 0 , 9";
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public Product findProductByPid(String pid) throws Exception {
		String sql="select *from product where pid=?";
		return qr.query(sql, new BeanHandler<Product>(Product.class),pid);
	}

	
	
	
	
	//分页查询当前分类下商品个数
	@Override
	public int findTotalRecords(String cid) throws Exception {
		String sql="select count(*) from product where cid=?";
		Long num=(Long) qr.query(sql, new ScalarHandler(), cid);
		return num.intValue();
	}
	@Override
	public List<Product> findProductsByCidWithPage(String cid, int startIndex, int pageSize)throws Exception {
		String sql="select * from product where cid = ? limit ? , ?";
		return qr.query(sql, new BeanListHandler<Product>(Product.class),cid,startIndex,pageSize);
		
	}

	@Override
	public int findTotalRecords() throws Exception {
		String sql="select count(*) from product";
		Long num=(Long) qr.query(sql, new ScalarHandler());
		return 0;
	}


	@Override
	public List<Product> findAllProductsWithPage() throws Exception {
		String sql="select * from product";
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	
	@Override
	public void addProduct(Product product) throws Exception {
		String sql="insert into product values(?,?,?,?,?,?,?,?,?,?)";
		Object []params={
						 product.getPid(),
						 product.getPname(),
						 product.getMarket_price(),
						 product.getShop_price(),
						 product.getPimage(),
						 product.getPdate(),
						 product.getIs_hot(),
						 product.getPdesc(),
						 product.getPflag(),
						 product.getCid()		
		};
		qr.update(sql, params);
	}

	@Override
	public List<Product> findProduct(String pname) throws Exception {
		String sql="select *from product where pname like ?";
		return qr.query(sql, new BeanListHandler<Product>(Product.class), "%"+pname+"%");
	}

	@Override
	public void updateProduct(Product p) throws Exception {
		String sql="update product set pname=? ,market_price=?,shop_price=?,is_hot=?,pdesc=? where pid=? ";
		Object []params={p.getPname(),p.getMarket_price(),p.getShop_price(),p.getIs_hot(),p.getPdesc(),p.getPid()};
		qr.update(sql, params);
	}

	@Override
	public void deleteProduct(Product p) throws Exception {
		String sql="delete from product where pid=?";
		qr.update(sql, p.getPid());
	}

	
}



















































