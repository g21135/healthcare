package kr.or.ddit.service.apply;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.ApplyVO;

public interface IApplyService extends Remote{
	public Object insertTrainer(ApplyVO vo) throws RemoteException;
	
	public List<ApplyVO> getAllApplyList() throws RemoteException;
	
	public int deleteApply(int apply_no) throws RemoteException;
}
