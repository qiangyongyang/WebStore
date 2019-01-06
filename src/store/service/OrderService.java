package store.service;

import java.util.List;

import store.Bean.Order;
import store.Bean.PageModel;
import store.Bean.User;

public interface OrderService {

	void saveOrder(Order order)throws Exception;

	PageModel findMyOrdersWithPage(User user, int curNum)throws Exception;

	Order findOrderByOid(String oid)throws Exception;

	void updateOrder(Order order)throws Exception;

	List<Order> findOrders()throws Exception;

	List<Order> findOrders(String state)throws Exception;

	

}
