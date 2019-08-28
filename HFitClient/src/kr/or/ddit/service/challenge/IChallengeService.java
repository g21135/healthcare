package kr.or.ddit.service.challenge;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.ChallengeVO;

public interface IChallengeService extends Remote{
	public List<ChallengeVO> selectAll() throws RemoteException;
	public boolean insert(ChallengeVO cv) throws RemoteException;
}
