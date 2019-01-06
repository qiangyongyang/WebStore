package store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.Bean.Category;
import store.service.CategoryService;
import store.service.impl.CategoryServiceImpl;
import store.utils.UUIDUtils;
import store.web.base.BaseServlet;

/**
 * Servlet implementation class AdminCategoryServlet
 */
public class AdminCategoryServlet extends BaseServlet {
	CategoryService categoryService=new CategoryServiceImpl();
	public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取全部分类信息SQLException
	
		List<Category>list=categoryService.getAllCats();
		//放入redis
		request.setAttribute("allCats", list);
		//转发到"/admin/category/list.jsp"
		return "/admin/category/list.jsp";
	}
	
	
	
	public String addCategoryUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/admin/category/add.jsp";
	}
	
	
	
	public String addCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取分类名称
				String cname=request.getParameter("cname");
				//创建分类ID
				String id=UUIDUtils.getId();
				Category c=new Category();
				c.setCid(id);
				c.setCname(cname);
				//调用业务层添加分类功能
			
				categoryService.addCategory(c);
				//重定向到查询全部分类信息
				response.sendRedirect("/WebStore/AdminCategoryServlet?method=findAllCats");
				return null;
	}
	
	
	
	
	public String editCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cid=request.getParameter("cid");
		
	
		Category c=categoryService.findCatsByCid(cid);
		request.setAttribute("category", c);
		
		return "/admin/category/edit.jsp";
	}
	
	
	
	public String updateCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cid=request.getParameter("cid");
		String cname=request.getParameter("cname");
		Category c=new Category();
		c.setCid(cid);
		c.setCname(cname);
		categoryService.updateCategory(c);
		
		response.sendRedirect("/WebStore/AdminCategoryServlet?method=findAllCats");
		return null;
	}
}









































































































































































