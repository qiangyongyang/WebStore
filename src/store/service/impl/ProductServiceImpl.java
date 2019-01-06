package store.service.impl;


import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import store.Bean.PageModel;
import store.Bean.Product;
import store.dao.ProductDao;
import store.dao.impl.ProductDaoImpl;
import store.service.ProductService;
import store.utils.JDBCUtils;

public class ProductServiceImpl implements ProductService{
	ProductDao productDao=new ProductDaoImpl();
		
	@Override
	public List<Product> findHots() throws Exception{
		return productDao.findHots();
	}

	@Override
	public List<Product> findNews() throws Exception {
		return productDao.findNews();
	}

	@Override
	public Product findProductByPid(String pid) throws Exception {
		
		return productDao.findProductByPid(pid);
	}

	@Override
	public PageModel findProductsByCidWithPage(String cid, int curNum)throws Exception {
		//1.统计当前分类下商品个数：select count(*) from product where cid=?;
		int totalRecords=productDao.findTotalRecords(cid);
		
		PageModel pm=new PageModel(curNum, totalRecords, 12); //计算分页参数
		//2.关联集合
		List<Product>list=productDao.findProductsByCidWithPage(cid,pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//3.关联url
		pm.setUrl("ProductServlet?method=findProductsByCidWithPage&cid="+cid);
		return pm;
	}

	
	@Override
	public List<Product> findAllProductsWithPage() throws Exception {
		return productDao.findAllProductsWithPage();
		
	}

	
	
	
	@Override
	public void addProduct(Product product) throws Exception {
		productDao.addProduct(product);
	}

	@Override
	public List<Product> findProduct(String pname) throws Exception {
		 return productDao.findProduct(pname);
	}

	@Override
	public void updateProduct(Product p) throws Exception {
		productDao.updateProduct(p);
	}

	@Override
	public void deleteProduct(Product p) throws Exception {
		productDao.deleteProduct(p);
	}

	
	
}














































































































