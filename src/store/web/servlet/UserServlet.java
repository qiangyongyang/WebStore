package store.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import store.Bean.User;
import store.service.UserService;
import store.service.impl.UserServiceImpl;
import store.utils.MailUtils;
import store.utils.MyBeanUtils;
import store.utils.UUIDUtils;
import store.web.base.BaseServlet;

public class UserServlet extends BaseServlet {
	
	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/register.jsp";
	}
	public String userRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		Map<String, String[]> map = request.getParameterMap();
		User user=new User();
		user.setUid(UUIDUtils.getId());
		user.setState(0);
		user.setCode(UUIDUtils.getCode());
		//时间类型的转化器
		MyBeanUtils.populate(user, map);
		
		UserService userService=new UserServiceImpl();
		try {
			userService.userRegist(user);
			//注册成功，发送邮件
			//MailUtils.sendMail(user.getEmail(), user.getCode());
			request.setAttribute("msg", "注册成功");
		} catch (Exception e) {
			request.setAttribute("msg", "用户注册失败");
			e.printStackTrace();
		}
		return "jsp/info.jsp";
	}
	
	
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/login.jsp";
	}
	
	public String userLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user=new User();
		User user02=null;
		MyBeanUtils.populate(user, request.getParameterMap());
		String code=request.getParameter("code").trim();
		String autoLogin = request.getParameter("auto_login");
		String remName=request.getParameter("rem_name");
		String success=(String) request.getSession().getAttribute("checkcode");
		
		UserService userService =new UserServiceImpl();
		user02=userService.userLogin(user);
		
		
		if(user02!=null){
			System.out.println(autoLogin +remName);
			if("on".equals(autoLogin)){
				Cookie cookie=new Cookie("auto", user02.getCode()+"#"+user02.getPassword());
				cookie.setMaxAge(60*60*7);
				cookie.setPath(request.getContextPath());
				response.addCookie(cookie);
			}
			
			if("on".equals(remName)){
				Cookie cookie=new Cookie("remuser", user02.getUsername()+"#"+user02.getPassword());
				cookie.setMaxAge(60*60*7);
				cookie.setPath(request.getContextPath());
				response.addCookie(cookie);
			}
			
			if(!success.equalsIgnoreCase(code)){
				request.setAttribute("msg", "老哥，验证码错了");
				return "/jsp/login.jsp";
			}
			
			request.getSession().setAttribute("loginUser", user02);
			response.sendRedirect("/WebStore/index.jsp");
			
			
			return null;
			
		}else{
			request.setAttribute("msg", "老哥，用户名或密码错了");
			return "/jsp/login.jsp";
		}
	}
	
	public String loginOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect("/WebStore/index.jsp");
		return null;
	}
}

































































































































