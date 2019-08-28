package kr.or.ddit.service.challenge;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.challenge.ChallengeDaoImpl;
import kr.or.ddit.dao.challenge.IChallengeDao;
import kr.or.ddit.vo.ChallengeVO;



public class ChallengeServiceImpl extends UnicastRemoteObject implements IChallengeService {
	
	private IChallengeDao ChallengeDao;	//사용 할 dao 멤버변수 선언
	private static IChallengeService service; //싱글톤패턴
	
	private ChallengeServiceImpl() throws RemoteException {
		ChallengeDao = ChallengeDaoImpl.getInstance();
	}
	
	public static IChallengeService getInstance() throws RemoteException{
		if(service == null) {
			service = new ChallengeServiceImpl();
		}
		return service;
	}

	@Override
	public List<ChallengeVO> selectAll() throws RemoteException{
		return ChallengeDao.selectAll();
	}

	@Override
	public boolean insert(ChallengeVO cv) throws RemoteException {
		return ChallengeDao.insert(cv);
	}

	@Override
	public ChallengeVO selectNum(int num) throws RemoteException {
		return ChallengeDao.selectNum(num);
	}

}
