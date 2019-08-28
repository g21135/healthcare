package kr.or.ddit.service.challApply;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.ChallApplyVO;
import kr.or.ddit.vo.ChallengeVO;

public interface IChallApplyService extends Remote {
	
	public void insert(ChallApplyVO cav) throws RemoteException;
	public List<ChallApplyVO> selectId(String id) throws RemoteException; 
	public ChallApplyVO selectCav(ChallApplyVO cav) throws RemoteException;  
	public List<ChallApplyVO> selectAll() throws RemoteException;  
}
