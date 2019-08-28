package kr.or.ddit.service.membership;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

import kr.or.ddit.dao.membership.IMembershipDao;
import kr.or.ddit.dao.membership.MembershipDaoImpl;
import kr.or.ddit.vo.MembershipVO;
import kr.or.ddit.vo.TrainerVO;



public class MemberShipServiceImpl extends UnicastRemoteObject implements IMemberShipService {
	
	private IMembershipDao MembershipDao;	//사용 할 dao 멤버변수 선언
	private static IMemberShipService service; //싱글톤패턴
	
	protected MemberShipServiceImpl() throws RemoteException {
		MembershipDao = MembershipDaoImpl.getInstance();
	}
	
	public static IMemberShipService getInstance() throws RemoteException{
		if(service == null) {
			service = new MemberShipServiceImpl();
		}
		return service;
	}
	
	@Override
	public List<TrainerVO> getListTrainer() {
		return MembershipDao.getListTrainer();
	}

	@Override
	public HashMap<String, Object> insertMemberShip(String mem_id, String time) throws RemoteException {
		return MembershipDao.insertMemberShip(mem_id, time);
	}
	
	@Override
	public List<MembershipVO> getMembershipList() throws RemoteException {
		return MembershipDao.getMembershipList();
	}

	
}
