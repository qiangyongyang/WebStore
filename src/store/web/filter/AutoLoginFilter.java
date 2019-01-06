package store.web.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import store.Bean.User;
import store.dao.UserDao;
import store.dao.impl.UserDaoImpl;
import store.utils.CookUtils;




public class AutoLoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest request=(HttpServletRequest) req;
			
			//先判断，现在session中有没有那个userBean
			User user=(User) request.getSession().getAttribute("loginUser");
			
			//还有，有效
			if(user!=null){
				chain.doFilter(request, response);
			}else{
				//session失效了，再看cookie
				//来请求时，先取出cookie，但是cookie有很多的key-cookie
				Cookie[] cookies = request.getCookies();
				
				//从一堆cookie里找出我们以前给浏览器发的那个cookie
				Cookie cookie=CookUtils.getCookieByName("auto_login",cookies);
				//第一次来，放行
				if(cookie == null){
					chain.doFilter(request, response);
				}else{
					 String value=cookie.getValue();
					 String username=value.split("#")[0];
					 String password=value.split("#")[1];
					 
					 //完成登陆
					 User user0=new User();
					 user0.setUsername(username);
					 user0.setPassword(password);
					 
					 UserDao dao=new UserDaoImpl();
					 user0=dao.userLogin(user0);
					 
					//存到session中
					 request.getSession().setAttribute("loginUser", user0);
					 
					 chain.doFilter(request, response);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			 chain.doFilter(req, response);
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {
	
	}
}
