package kr.or.ddit.service.hope;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import kr.or.ddit.dao.hope.HopeDaoImpl;
import kr.or.ddit.dao.hope.IHopeDao;
import kr.or.ddit.vo.HopeVO;


public class HopeServiceImpl extends UnicastRemoteObject implements IHopeService {
	
	private IHopeDao HopeDao;	//사용 할 dao 멤버변수 선언
	private static IHopeService service; //싱글톤패턴
	
	protected HopeServiceImpl() throws RemoteException {
		HopeDao = HopeDaoImpl.getInstance();
	}
	
	public static IHopeService getInstance() throws RemoteException{
		if(service == null) {
			service = new HopeServiceImpl();
		}
		return service;
	}
	
	@Override
	public Object insertHope(HopeVO vo){
		return HopeDao.insertHope(vo);
	}
	
	@Override
	public HopeVO getMemberHope(String mem_id) throws RemoteException {
		return HopeDao.getMemberHope(mem_id);
	}

}
