package kr.or.ddit.dao.trainer;


import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.TrainerVO;

public interface ITrainerDao {

	TrainerVO login(String id, String pass);
	
	public List<TrainerVO> getAllTrainerList();
	
	public int updateTrainer(TrainerVO vo);
	
	public int insertTrainer(TrainerVO vo);
	
	public TrainerVO getTrainer(String name);
	

}
