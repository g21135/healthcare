package kr.or.ddit.service.apply;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.apply.ApplyDaoImpl;
import kr.or.ddit.dao.apply.IApplyDao;
import kr.or.ddit.vo.ApplyVO;



public class ApplyServiceImpl extends UnicastRemoteObject implements IApplyService {

	private IApplyDao ApplyDao;	//사용 할 dao 멤버변수 선언
	private static IApplyService service; //싱글톤패턴
	
	protected ApplyServiceImpl() throws RemoteException {
		ApplyDao = ApplyDaoImpl.getInstance();
	}
	
	public static IApplyService getInstance() throws RemoteException{
		if(service == null) {
			service = new ApplyServiceImpl();
		}
		return service;
	}
	
	@Override
	public Object insertTrainer(ApplyVO vo) {
		return ApplyDao.insertTrainer(vo);
	}

	@Override
	public List<ApplyVO> getAllApplyList() throws RemoteException {
		return ApplyDao.getAllApplyList();
	}

	@Override
	public int deleteApply(int apply_no) throws RemoteException {
		return ApplyDao.deleteApply(apply_no);
	}
}
