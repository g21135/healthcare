package kr.or.ddit.dao.membership;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

import kr.or.ddit.vo.MembershipVO;
import kr.or.ddit.vo.TrainerVO;

public interface IMembershipDao {
	public List<TrainerVO> getListTrainer();
	
	public HashMap<String, Object> insertMemberShip(String mem_id, String time);
	
	public List<MembershipVO> getMembershipList();
}
