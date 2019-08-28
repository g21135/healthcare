package kr.or.ddit.service.membership;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import kr.or.ddit.vo.MembershipVO;
import kr.or.ddit.vo.TrainerVO;

public interface IMemberShipService extends Remote{
	public List<TrainerVO> getListTrainer() throws RemoteException ;
	
	public HashMap<String, Object> insertMemberShip(String mem_id, String time) throws RemoteException;
	
	public List<MembershipVO> getMembershipList() throws RemoteException;
}
