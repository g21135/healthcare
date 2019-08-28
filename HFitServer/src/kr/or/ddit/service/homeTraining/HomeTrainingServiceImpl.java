package kr.or.ddit.service.homeTraining;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.homeTraining.HomeTrainingDaoImpl;
import kr.or.ddit.dao.homeTraining.IHomeTrainingDao;
import kr.or.ddit.vo.HomeTrainingVO;



public class HomeTrainingServiceImpl extends UnicastRemoteObject implements IHomeTrainingService {
	
	private IHomeTrainingDao HomeTrainingDao;	//사용 할 dao 멤버변수 선언
	private static IHomeTrainingService service; //싱글톤패턴
	
	protected HomeTrainingServiceImpl() throws RemoteException {
		HomeTrainingDao = HomeTrainingDaoImpl.getInstance();
	}
	
	public static IHomeTrainingService getInstance() throws RemoteException{
		if(service == null) {
			service = new HomeTrainingServiceImpl();
		}
		return service;
	}

	@Override
	public List<HomeTrainingVO> selectAll() throws RemoteException {
		return HomeTrainingDao.selectAll();
	}

}
