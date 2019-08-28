package kr.or.ddit.service.check;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.check.CheckDaoImpl;
import kr.or.ddit.dao.check.ICheckDao;
import kr.or.ddit.vo.ChallApplyVO;
import kr.or.ddit.vo.ChallengeVO;
import kr.or.ddit.vo.CheckVO;



public class CheckServiceImpl extends UnicastRemoteObject implements ICheckService {
	
	private ICheckDao CheckDao;	//사용 할 dao 멤버변수 선언
	private static ICheckService service; //싱글톤패턴
	
	protected CheckServiceImpl() throws RemoteException {
		CheckDao = CheckDaoImpl.getInstance();
	}
	
	public static ICheckService getInstance() throws RemoteException{
		if(service == null) {
			service = new CheckServiceImpl();
		}
		return service;
	}

	@Override
	public List<CheckVO> selectAll(ChallApplyVO cav) throws RemoteException {
		
		return CheckDao.selectAll(cav);
	}

	@Override
	public boolean insert(CheckVO cv) throws RemoteException {
		return CheckDao.insert(cv);
	}

	@Override
	public List<CheckVO> selectAll() throws RemoteException {
		return CheckDao.selectAll();
	}

	@Override
	public void update(CheckVO cv) throws RemoteException {
		CheckDao.update(cv);
	}

	@Override
	public List<CheckVO> selectIdChallNo(ChallApplyVO cav) throws RemoteException {
		return CheckDao.selectIdChallNo(cav);
	}

}
