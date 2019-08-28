package kr.or.ddit.service.match;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.match.IMatchDao;
import kr.or.ddit.dao.match.MatchDaoImpl;
import kr.or.ddit.vo.MatchVO;



public class MatchServiceImpl extends UnicastRemoteObject implements IMatchService {
	
	private IMatchDao MatchDao;	//사용 할 dao 멤버변수 선언
	private static IMatchService service; //싱글톤패턴
	
	protected MatchServiceImpl() throws RemoteException {
		MatchDao = MatchDaoImpl.getInstance();
	}
	
	public static IMatchService getInstance() throws RemoteException{
		if(service == null) {
			service = new MatchServiceImpl();
		}
		return service;
	}

	@Override
	public List<MatchVO> selectAll() throws RemoteException {
		return MatchDao.selectAll();
	}
	@Override
	public MatchVO selectTrainer(String id) throws RemoteException {
		return MatchDao.selectTrainer(id);
	}


	@Override
	public List<MatchVO> matchTrainer(MatchVO mc) throws RemoteException {
		return MatchDao.matchTrainer(mc);
	}
	
	@Override
	public List<MatchVO> getMatchList() throws RemoteException {
		return MatchDao.getMatchList();
	}

	@Override
	public MatchVO insertMatch(MatchVO vo) throws RemoteException {
		return MatchDao.insertMatch(vo);
	}

	
	
	

}
