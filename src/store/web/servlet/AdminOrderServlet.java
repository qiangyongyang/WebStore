package store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import store.Bean.Order;
import store.service.OrderService;
import store.service.impl.OrderServiceImpl;
import store.web.base.BaseServlet;


public class AdminOrderServlet extends BaseServlet {
	OrderService orderService=new OrderServiceImpl();
	
	public String findOrders(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String state=request.getParameter("state");
		List<Order>list=null;
		
		if(null==state || "".equals(state)){
			list=orderService.findOrders();
		}else{
			list=orderService.findOrders(state);
		}
		
		
		request.setAttribute("allOrders", list);
		return "/admin/order/list.jsp";
	}

	
	
	
	
	
	public String findOrderByOidWithAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//服务端获取到订单ID,
				String oid=request.getParameter("id");
				//查询这个订单下所有的订单项以及订单项对应的商品信息,返回集合
				OrderService OrderService=new OrderServiceImpl();
				Order order=OrderService.findOrderByOid(oid);
				//将返回的集合转换为JSON格式字符串,响应到客户端
				String jsonStr=JSONArray.fromObject(order.getList()).toString();
				//响应到客户端
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().println(jsonStr);
				return null;
		
	}
	
	
	
	
	public String updateOrderByOid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取订单ID
				String oid=request.getParameter("oid");
				//根据订单ID查询订单
				OrderService OrderService=new OrderServiceImpl();
				Order order=OrderService.findOrderByOid(oid);
				//设置订单状态
				order.setState(3);
				//修改订单信息
				OrderService.updateOrder(order);
				//重新定向到查询已发货订单
				response.sendRedirect("/WebStore/AdminOrderServlet?method=findOrders&state=3");
				return null;
	}
}





































































