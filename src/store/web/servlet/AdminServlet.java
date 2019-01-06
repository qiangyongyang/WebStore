package store.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.Bean.User;
import store.service.UserService;
import store.service.impl.UserServiceImpl;
import store.utils.MyBeanUtils;
import store.web.base.BaseServlet;


public class AdminServlet extends BaseServlet {
	
	
	public String adminLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user=new User();
		User user02=null;
		MyBeanUtils.populate(user, request.getParameterMap());
		
		
		UserService userService =new UserServiceImpl();
		user02=userService.userLogin(user);
		
		if(user02!=null){
			request.getSession().setAttribute("admin", user02);
			return "admin/home.jsp";
			
		}else{
			request.setAttribute("msg", "老哥，用户名或密码错了");
			return "/admin/index.jsp";
		}
		
	}

}
