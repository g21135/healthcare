package kr.or.ddit.service.cart;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.cart.CartDaoImpl;
import kr.or.ddit.dao.cart.ICartDao;
import kr.or.ddit.vo.CartVO;


public class CartServiceImpl extends UnicastRemoteObject implements ICartService {

	private ICartDao cartDao; // cartdao 에서 사용할 dao 멤버변수 선언
	
	private static ICartService service; //싱글톤 패턴 
	
	private CartServiceImpl() throws RemoteException{
		cartDao = CartDaoImpl.getInstance();
	}
	public static ICartService getInstance() throws RemoteException {
		if(service == null) {
				service = new CartServiceImpl();
		}
		return service;
	}
	@Override
	public int InsertCart(CartVO cv) throws RemoteException {
		return cartDao.InsertCart(cv);
	}
	@Override
	public int DeleteCart(CartVO cv) throws RemoteException {
		return cartDao.DeleteCart(cv);
	}
	@Override
	public List<CartVO> AllCartListget(CartVO cv) throws RemoteException {
		return cartDao.AllCartListget(cv);
	}
	@Override
	public int CartSelectUpdate(CartVO cv) throws RemoteException {
		return cartDao.CartSelectUpdate(cv);
	}
	@Override
	public int CartCheckUpdate(CartVO cv) throws RemoteException {
		return cartDao.CartCheckUpdate(cv);
	}
	@Override
	public int DeleteOrder() throws RemoteException {
		return cartDao.DeleteOrder();
	}
	@Override
	public List<CartVO> CheckCartListget(CartVO cv) throws RemoteException {
		return cartDao.CheckCartListget(cv);
	}
	
}
