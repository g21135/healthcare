package kr.or.ddit.dao.cart;

import java.util.List;

import kr.or.ddit.vo.CartVO;

public interface ICartDao {

	public int InsertCart(CartVO cv);

	public int DeleteCart(CartVO cv);

	

	public List<CartVO> AllCartListget(CartVO cv);
	
	public int CartSelectUpdate(CartVO cv);

	public int CartCheckUpdate(CartVO cv);

	public int DeleteOrder();

	public List<CartVO> CheckCartListget(CartVO cv);

	
	
}
