package kr.or.ddit.service.cartDetail;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import kr.or.ddit.dao.cartDetail.CartDetailDaoImpl;
import kr.or.ddit.dao.cartDetail.ICartDetailDao;



public class CartDetailServiceImpl extends UnicastRemoteObject implements ICartDetailService {

	private ICartDetailDao CartDetailDao;	//사용 할 dao 멤버변수 선언
	private static ICartDetailService service; //싱글톤패턴
	
	protected CartDetailServiceImpl() throws RemoteException {
		CartDetailDao = CartDetailDaoImpl.getInstance();
	}
	
	public static ICartDetailService getInstance() throws RemoteException{
		if(service == null) {
			service = new CartDetailServiceImpl();
		}
		return service;
	}
}
