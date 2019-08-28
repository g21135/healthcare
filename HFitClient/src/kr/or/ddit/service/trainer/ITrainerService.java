package kr.or.ddit.service.trainer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.TrainerVO;

public interface ITrainerService extends Remote {
	public TrainerVO login(String id, String pass) throws RemoteException;

	public List<TrainerVO> getAllTrainerList() throws RemoteException;

	public int updateTrainer(TrainerVO vo) throws RemoteException;

	public int insertTrainer(TrainerVO vo) throws RemoteException;

	public TrainerVO getTrainer(String name) throws RemoteException;

}
