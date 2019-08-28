package kr.or.ddit.service.challApply;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.challApply.ChallApplyDaoImpl;
import kr.or.ddit.dao.challApply.IChallApplyDao;
import kr.or.ddit.vo.ChallApplyVO;


public class ChallApplyServiceImpl extends UnicastRemoteObject implements IChallApplyService {

	private IChallApplyDao ChallApplyDao;	//사용 할 dao 멤버변수 선언
	private static IChallApplyService service; //싱글톤패턴
	
	protected ChallApplyServiceImpl() throws RemoteException {
		ChallApplyDao = ChallApplyDaoImpl.getInstance();
	}
	
	public static IChallApplyService getInstance() throws RemoteException{
		if(service == null) {
			service = new ChallApplyServiceImpl();
		}
		return service;
	}

	@Override
	public void insert(ChallApplyVO cav) throws RemoteException {
		ChallApplyDao.insert(cav);
	}

	@Override
	public List<ChallApplyVO> selectId(String id) throws RemoteException {
		return ChallApplyDao.selectId(id);
	}

	@Override
	public ChallApplyVO selectCav(ChallApplyVO cav) throws RemoteException {
		return ChallApplyDao.selectCav(cav);
	}

	@Override
	public List<ChallApplyVO> selectAll() throws RemoteException {
		return ChallApplyDao.selectAll();
	}
}
