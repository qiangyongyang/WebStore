package store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;
import store.Bean.Category;
import store.service.CategoryService;
import store.service.impl.CategoryServiceImpl;
import store.utils.JedisUtils;
import store.web.base.BaseServlet;

public class CategoryServlet extends BaseServlet {
	
	public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("application/json;charset=utf-8");
		String jsonStr="";
		Jedis j = JedisUtils.getJedis();
		jsonStr = j.get("allCats");
		if(null==jsonStr||"".equals(jsonStr)){
			System.out.println("缓存中没有数据");
			//查询所有分类
			CategoryService CategoryService=new CategoryServiceImpl();
			List<Category> list = CategoryService.getAllCats();
			//将集合中的所有分类信息转换为JSON格式的字符串数据
			jsonStr=JSONArray.fromObject(list).toString();
			j.set("allCats", jsonStr);
			
		}
		//将字符串数据响应到客户端
		response.getWriter().println(jsonStr);
		
		JedisUtils.closeJedis(j);
		return null;
	}

}







































































