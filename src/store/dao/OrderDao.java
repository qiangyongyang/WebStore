package store.dao;

import java.sql.Connection;
import java.util.List;

import store.Bean.Order;
import store.Bean.OrderItem;
import store.Bean.User;

public interface OrderDao {

	void saveOrder(Connection conn, Order order)throws Exception;

	void saveOrderItem(Connection conn, OrderItem item)throws Exception;

	int getTotalRecords(User user)throws Exception;

	List findMyOrdersWithPage(User user, int startIndex, int pageSize)throws Exception;

	Order findOrderByOid(String oid)throws Exception;

	void updateOrder(Order order)throws Exception;

	List<Order> findOrders()throws Exception;

	List<Order> findOrders(String state)throws Exception;



	
	

	

}
