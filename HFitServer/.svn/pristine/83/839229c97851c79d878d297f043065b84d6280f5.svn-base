package kr.or.ddit.service.healthInfo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import kr.or.ddit.dao.healthInfo.HealthInfoDaoImpl;
import kr.or.ddit.dao.healthInfo.IhealthInfoDao;


public class HealthInfoServiceImpl extends UnicastRemoteObject implements IHealthInfoService {
	
	private IhealthInfoDao healthInfoDao;	//사용 할 dao 멤버변수 선언
	private static IHealthInfoService service; //싱글톤패턴
	
	protected HealthInfoServiceImpl() throws RemoteException {
		healthInfoDao = HealthInfoDaoImpl.getInstance();
	}
	
	public static IHealthInfoService getInstance() throws RemoteException{
		if(service == null) {
			service = new HealthInfoServiceImpl();
		}
		return service;
	}

}
