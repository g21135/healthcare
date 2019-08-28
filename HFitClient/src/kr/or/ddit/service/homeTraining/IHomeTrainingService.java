package kr.or.ddit.service.homeTraining;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.HomeTrainingVO;

public interface IHomeTrainingService extends Remote {
	public List<HomeTrainingVO> selectAll() throws RemoteException;
}
