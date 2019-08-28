package kr.or.ddit.service.coupon;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import kr.or.ddit.dao.coupon.CouponDaoImpl;
import kr.or.ddit.dao.coupon.ICouponDao;



public class CouponServiceImpl extends UnicastRemoteObject implements ICouponService {
	
	private ICouponDao CouponDao;	//사용 할 dao 멤버변수 선언
	private static ICouponService service; //싱글톤패턴
	
	protected CouponServiceImpl() throws RemoteException {
		CouponDao = CouponDaoImpl.getInstance();
	}
	
	public static ICouponService getInstance() throws RemoteException{
		if(service == null) {
			service = new CouponServiceImpl();
		}
		return service;
	}

}
