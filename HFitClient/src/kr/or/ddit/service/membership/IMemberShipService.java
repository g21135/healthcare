package kr.or.ddit.service.membership;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

import kr.or.ddit.vo.MembershipVO;
import kr.or.ddit.vo.TrainerVO;

public interface IMemberShipService {
	public List<TrainerVO> getListTrainer();

	public HashMap<String, Object> insertMemberShip(String mem_id, String time) throws RemoteException;
	
	public List<MembershipVO> getMembershipList() throws RemoteException;
}
