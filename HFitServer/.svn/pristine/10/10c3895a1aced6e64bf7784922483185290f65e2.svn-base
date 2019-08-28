package kr.or.ddit.service.divcode;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import kr.or.ddit.dao.divcode.DivcodeDaoImpl;
import kr.or.ddit.dao.divcode.IDivcodeDao;


public class DivcodeServiceImpl extends UnicastRemoteObject implements IDivcodeService {
	
	private IDivcodeDao DivcodeDao;	//사용 할 dao 멤버변수 선언
	private static IDivcodeService service; //싱글톤패턴
	
	protected DivcodeServiceImpl() throws RemoteException {
		DivcodeDao = DivcodeDaoImpl.getInstance();
	}
	
	public static IDivcodeService getInstance() throws RemoteException{
		if(service == null) {
			service = new DivcodeServiceImpl();
		}
		return service;
	}

}
