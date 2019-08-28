package kr.or.ddit.dao.apply;

import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.ApplyVO;

public interface IApplyDao {
	public Object insertTrainer(ApplyVO vo);
	
	public List<ApplyVO> getAllApplyList();
	
	public int deleteApply(int apply_no);
}
