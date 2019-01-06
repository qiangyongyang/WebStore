package store.Bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
	//个数不确定的购物项
	private Map<String , CartItem>map=new HashMap<String , CartItem>();
	//总价，积分
	private double total;
	
	


	
	

	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	public double getTotal() {
		total=0;
		//获取map中所有的购物项
		Collection<CartItem> values = map.values();
		
		for (CartItem cartItem : values) {
			total+=cartItem.getSubTotal();
		}
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}




	/*
	 * 添加购物项到购物车：
	 * 当用户点击加入购物车连接时，可将当前的购物类别的商品id，商品数量，发送到服务端。服务端根据商品id查询到商品信息
	 * 
	 */
	public void addCartItemToCart(CartItem cartItem){
		//获取到正在向购物车中添加的商品的pid
		String pid=cartItem.getProduct().getPid();
		
		/*
		 * 将当前购物项加入购物车之前，判断之前是否买过这类商品：
		 * 1.如果没有买过：map.put(pid,cartItem)
		 * 2.如果买过：获取原先的数量，i++
		 */
		if(map.containsKey(pid)){
			//获取原先的购物项
			CartItem oldItem=map.get(pid);
			
			oldItem.setNum(oldItem.getNum()+cartItem.getNum());
		}else{
			map.put(pid, cartItem);
		}
	}
	
	//返回map中所有的值
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	
	
	/*
	 * 移除购物项:
	 * 当用户点击移除购物项连接时，可将当前的购物类别的商品id发送到服务端
	 * 
	 */
	public void removeCartItem(String pid){
		map.remove(pid);
	}
	
	
	
	
	//清空购物车
	public void clearCart(){
		map.clear();
	}
	
}
