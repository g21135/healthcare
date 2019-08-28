package kr.or.ddit.service.remain;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import kr.or.ddit.dao.remain.IRemainDao;
import kr.or.ddit.dao.remain.RemainDaoImpl;


public class RemainServiceImpl extends UnicastRemoteObject implements IRemainService {
	
	private IRemainDao RemainDao;	//사용 할 dao 멤버변수 선언
	private static IRemainService service; //싱글톤패턴
	
	protected RemainServiceImpl() throws RemoteException {
		RemainDao = RemainDaoImpl.getInstance();
	}
	
	public static IRemainService getInstance() throws RemoteException{
		if(service == null) {
			service = new RemainServiceImpl();
		}
		return service;
	}

}
