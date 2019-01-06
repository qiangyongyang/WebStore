package store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.Bean.PageModel;
import store.Bean.Product;
import store.service.ProductService;
import store.service.impl.ProductServiceImpl;
import store.web.base.BaseServlet;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends BaseServlet {
	
	public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		 * 获取商品pid
		 * 通过pid查询商品信息
		 * 将查询到的商品房入request
		 * 转发到/jsp/product_info.jsp
		 */
		String pid=request.getParameter("pid");
		ProductService productService=new ProductServiceImpl();
		Product product=productService.findProductByPid(pid);
		
		request.setAttribute("product", product);
		
		return "/jsp/product_info.jsp";
	}

	public String findProductsByCidWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取cid和num（当前页）
		String cid=request.getParameter("cid");
		int curNum=Integer.parseInt(request.getParameter("num"));
		//以分页形式查询当前类别下的商品信息
		ProductService productService =new ProductServiceImpl();
		//返回pageModel对象(1_当前商品信息 2_分页 3_url）
		PageModel pm=productService.findProductsByCidWithPage(cid,curNum);
		
		request.setAttribute("page", pm);
		
		return "/jsp/product_list.jsp";
	}
	
	
	
	
	public String findProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pname=request.getParameter("pname");
		
		
		
		ProductService productService=new ProductServiceImpl();
		List<Product>list2=productService.findProduct(pname);
		request.setAttribute("product2", list2);
		return "/jsp/product_serach.jsp";
		
	}
}




















