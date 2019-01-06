package store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import store.Bean.Category;
import store.Bean.Product;
import store.service.CategoryService;
import store.service.ProductService;
import store.service.impl.CategoryServiceImpl;
import store.service.impl.ProductServiceImpl;
import store.web.base.BaseServlet;

public class IndexServlet extends BaseServlet{
	
	@Override
	public String execute(HttpServletRequest requset, HttpServletResponse response) throws Exception {
		//获取全部分类信息
		CategoryService categoryService1=new CategoryServiceImpl();
		List<Category>list=categoryService1.getAllCats();
		
		requset.setAttribute("allCats", list);
		//转发到真正的首页
		//return "/jsp/index.jsp";
		
		
		
		
		
		
		//查询最新商品，查询最热商品，返回两个集合
		ProductService productService=new ProductServiceImpl();
		List<Product> list1=productService.findHots();
		List<Product> list2=productService.findNews();
		
		requset.setAttribute("hots", list1);
		requset.setAttribute("news", list2);
		
		
		
		
		return "/jsp/index.jsp";
	}

	
	

}
