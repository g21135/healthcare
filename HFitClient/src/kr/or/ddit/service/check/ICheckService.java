package kr.or.ddit.service.check;

import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.ChallApplyVO;
import kr.or.ddit.vo.ChallengeVO;
import kr.or.ddit.vo.CheckVO;

public interface ICheckService {
	public List<CheckVO> selectAll(ChallApplyVO cav) throws RemoteException;
	public boolean insert(CheckVO cv) throws RemoteException;
	public List<CheckVO>  selectAll() throws RemoteException;
	public void update(CheckVO cv) throws RemoteException;	
	public List<CheckVO> selectIdChallNo(ChallApplyVO cav) throws RemoteException;	
}
