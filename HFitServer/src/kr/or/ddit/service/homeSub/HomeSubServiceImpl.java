package kr.or.ddit.service.homeSub;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.homeSub.HomeSubDaoImpl;
import kr.or.ddit.dao.homeSub.IHomeSubDao;
import kr.or.ddit.vo.HomeSubVO;


public class HomeSubServiceImpl extends UnicastRemoteObject implements IHomeSubService {
	
	private IHomeSubDao HomeSubDao;	//사용 할 dao 멤버변수 선언
	private static IHomeSubService service; //싱글톤패턴
	
	protected HomeSubServiceImpl() throws RemoteException {
		HomeSubDao = HomeSubDaoImpl.getInstance();
	}
	
	public static IHomeSubService getInstance() throws RemoteException{
		if(service == null) {
			service = new HomeSubServiceImpl();
		}
		return service;
	}

	@Override
	public void insert(HomeSubVO hsv) throws RemoteException {
		HomeSubDao.insert(hsv);
	}

	@Override
	public List<HomeSubVO> selectAll() throws RemoteException {
		return HomeSubDao.selectAll();
	}

	@Override
	public void delete(HomeSubVO hsv) throws RemoteException {
		HomeSubDao.delete(hsv);
		
	}

}
