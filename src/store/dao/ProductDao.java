package store.dao;

import java.util.List;

import store.Bean.Product;

public interface ProductDao {
	List<Product> findHots()throws Exception;

	List<Product> findNews()throws Exception;

	Product findProductByPid(String pid)throws Exception;

	int findTotalRecords(String cid)throws Exception;

	List<Product> findProductsByCidWithPage(String cid, int startIndex, int pageSize)throws Exception;

	int findTotalRecords()throws Exception;

	List<Product> findAllProductsWithPage()throws Exception;

	void addProduct(Product product)throws Exception;

	List<Product> findProduct(String pname)throws Exception;

	void updateProduct(Product p)throws Exception;

	void deleteProduct(Product p)throws Exception;
}
