package kr.or.ddit.service.msgHistory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import kr.or.ddit.dao.msgHistory.IMsgHistoryDao;
import kr.or.ddit.dao.msgHistory.MsgHistoryDaoImpl;



public class MsgHistoryServiceImpl extends UnicastRemoteObject implements IMsgHistoryService {
	
	private IMsgHistoryDao MsgHistoryDao;	//사용 할 dao 멤버변수 선언
	private static IMsgHistoryService service; //싱글톤패턴
	
	protected MsgHistoryServiceImpl() throws RemoteException {
		MsgHistoryDao = MsgHistoryDaoImpl.getInstance();
	}
	
	public static IMsgHistoryService getInstance() throws RemoteException{
		if(service == null) {
			service = new MsgHistoryServiceImpl();
		}
		return service;
	}

}
