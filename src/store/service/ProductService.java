package store.service;

import java.util.List;

import store.Bean.PageModel;
import store.Bean.Product;

public interface ProductService {

	List<Product> findHots()throws Exception;

	List<Product> findNews()throws Exception;

	Product findProductByPid(String pid)throws Exception;

	PageModel findProductsByCidWithPage(String cid, int curNum)throws Exception;

	List<Product> findAllProductsWithPage()throws Exception;

	void addProduct(Product product)throws Exception;

	List<Product> findProduct(String pname)throws Exception;

	void updateProduct(Product p)throws Exception;

	void deleteProduct(Product p)throws Exception;

	

}
