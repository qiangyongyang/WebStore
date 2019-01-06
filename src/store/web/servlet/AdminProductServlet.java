package store.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import store.Bean.Category;
import store.Bean.PageModel;
import store.Bean.Product;
import store.service.CategoryService;
import store.service.ProductService;
import store.service.impl.CategoryServiceImpl;
import store.service.impl.ProductServiceImpl;
import store.utils.UUIDUtils;
import store.utils.UploadUtils;
import store.web.base.BaseServlet;

/**
 * Servlet implementation class AdminProductServlet
 */
public class AdminProductServlet extends BaseServlet {
	
	public String findAllProductsWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
				//调用业务层查全部商品信息返回PageModel
				ProductService ProductService=new ProductServiceImpl();
				List<Product>list=ProductService.findAllProductsWithPage();
				//将PageModel放入request
				request.setAttribute("list", list);
				//转发到/admin/product/list.jsp
				return "/admin/product/list.jsp";
	}
	
	
	//商品添加页面跳转
	public String addProductUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取全部分类信息
		CategoryService categoryService=new CategoryServiceImpl();
		List<Category>list=categoryService.getAllCats();
		
		//将分类信息存放到request中，
		request.setAttribute("allCats", list);
		//转发
		return "/admin/product/add.jsp";
	}
	
	//添加商品
	public String addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//存储表单中数据
				Map<String,String> map=new HashMap<String,String>();
				//携带表单中的数据向servcie,dao
				Product product=new Product();
				try {
					//利用req.getInputStream();获取到请求体中全部数据,进行拆分和封装
					DiskFileItemFactory fac=new DiskFileItemFactory();
					ServletFileUpload upload=new ServletFileUpload(fac);
					List<FileItem> list=upload.parseRequest(request);
					//4_遍历集合
					for (FileItem item : list) {
						if(item.isFormField()){
							//5_如果当前的FileItem对象是普通项
							//将普通项上name属性的值作为键,将获取到的内容作为值,放入MAP中
							// {username<==>tom,password<==>1234}
							map.put(item.getFieldName(), item.getString("utf-8"));
						}else{
							//6_如果当前的FileItem对象是上传项
							
							//获取到原始的文件名称
							String oldFileName=item.getName();
							//获取到要保存文件的名称   1222.doc  123421342143214.doc
							String newFileName=UploadUtils.getUUIDName(oldFileName);
							
							//通过FileItem获取到输入流对象,通过输入流可以获取到图片二进制数据
							InputStream is=item.getInputStream();
							//获取到当前项目下products/3下的真实路径
							//D:\tomcat\tomcat71_sz07\webapps\store_v5\products\3
							String realPath=getServletContext().getRealPath("/products/3/");
							String dir=UploadUtils.getDir(newFileName); // /f/e/d/c/4/9/8/4
							String path=realPath+dir; //D:\tomcat\tomcat71_sz07\webapps\store_v5\products\3/f/e/d/c/4/9/8/4
							//内存中声明一个目录
							File newDir=new File(path);
							if(!newDir.exists()){
								newDir.mkdirs();
							}
							//在服务端创建一个空文件(后缀必须和上传到服务端的文件名后缀一致)
							File finalFile=new File(newDir,newFileName);
							if(!finalFile.exists()){
								finalFile.createNewFile();
							}
							//建立和空文件对应的输出流
							OutputStream os=new FileOutputStream(finalFile);
							//将输入流中的数据刷到输出流中
							IOUtils.copy(is, os);
							//释放资源
							IOUtils.closeQuietly(is);
							IOUtils.closeQuietly(os);
							//向map中存入一个键值对的数据 userhead<===> /image/11.bmp
							// {username<==>tom,password<==>1234,userhead<===>image/11.bmp}
							map.put("pimage", "/products/3/"+dir+"/"+newFileName);
						}
					}

					
					//7_利用BeanUtils将MAP中的数据填充到Product对象上
					BeanUtils.populate(product, map);
					product.setPid(UUIDUtils.getId());
					product.setPdate(new Date());
					product.setPflag(0);
					
					//8_调用servcie_dao将user上携带的数据存入数据仓库,重定向到查询全部商品信息路径
					ProductService productService=new ProductServiceImpl();
					productService.addProduct(product);
					
					response.sendRedirect("/WebStore/AdminProductServlet?method=findAllProductsWithPage&num=1");
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return null;
			}
	
	
	
	
	
	public String editProductUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取全部分类信息
		CategoryService categoryService=new CategoryServiceImpl();
		List<Category>list=categoryService.getAllCats();
		
		//将分类信息存放到request中，
		request.setAttribute("allCats", list);
		
		
		
		String pid =request.getParameter("pid");
		
		ProductService ProductService=new ProductServiceImpl();
		Product p=ProductService.findProductByPid(pid);
		
		request.setAttribute("product", p);
		
		
		//转发
		return "/admin/product/edit.jsp";
	}
	public String editProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取全部分类信息
		CategoryService categoryService=new CategoryServiceImpl();
		List<Category>list=categoryService.getAllCats();
				
		//将分类信息存放到request中，
		request.setAttribute("allCats", list);
		
		
		
		String pid =request.getParameter("pid");
		
		ProductService ProductService=new ProductServiceImpl();
		Product p=ProductService.findProductByPid(pid);
		
		request.setAttribute("p", p);
		
		
		
		return "/admin/product/edit.jsp";
	}
	
	
	
	
	public String updateProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid=request.getParameter("pid");
		String pname=request.getParameter("pname");
		double market_price=Double.parseDouble(request.getParameter("market_price"));
		double shop_price=Double.parseDouble(request.getParameter("shop_price"));
		int is_hot=Integer.parseInt(request.getParameter("is_hot"));
		String pdesc=request.getParameter("pdesc");
		
		
		Product p=new Product();
		p.setPid(pid);
		p.setPname(pname);
		p.setMarket_price(market_price);
		p.setShop_price(shop_price);
		p.setIs_hot(is_hot);
		p.setPdesc(pdesc);
		
		ProductService productService=new ProductServiceImpl();
		productService.updateProduct(p);
		
		
		response.sendRedirect("/WebStore/AdminProductServlet?method=findAllProductsWithPage&num=1");
		return null;
	}
	
	
	
	
	public String deleteProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid=request.getParameter("pid");
		
		ProductService productService=new ProductServiceImpl();
		Product p=productService.findProductByPid(pid);
		
		productService.deleteProduct(p);
		
		response.sendRedirect("/WebStore/AdminProductServlet?method=findAllProductsWithPage&num=1");
		return null;
	}
}
































































































































