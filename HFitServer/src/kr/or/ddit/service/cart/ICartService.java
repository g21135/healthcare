package kr.or.ddit.service.cart;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.CartVO;

public interface ICartService extends Remote {

	
	//장바구니에 추가
	public int InsertCart(CartVO cv) throws RemoteException;
	
	//상품 id를 받아 삭제
	public int DeleteCart(CartVO cv) throws RemoteException;
	
	public int DeleteOrder() throws RemoteException;
	
	//테이블이름을 가져옴
	public List<CartVO> AllCartListget(CartVO cv)throws RemoteException;
	
	public List<CartVO> CheckCartListget(CartVO cv)throws RemoteException;
	
	public int CartSelectUpdate(CartVO cv)throws RemoteException;
	
	public int CartCheckUpdate(CartVO cv)throws RemoteException;
}
