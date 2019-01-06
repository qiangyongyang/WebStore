package store.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import store.Bean.User;


public class AdminPriviledgeFilter implements Filter {

  
    public AdminPriviledgeFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		/*
		 * 判断Session中是否存在登录成功的用户：
		 * 1.如果存在，放行
		 * 2.不存在，就弹出提示信息
		 */
		User user=(User) req.getSession().getAttribute("admin");
		if(user == null){
			req.setAttribute("msg", "请在登陆后访问");
			req.getRequestDispatcher("/admin/index.jsp").forward(request, response);
		}else{
			chain.doFilter(request, response);
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
