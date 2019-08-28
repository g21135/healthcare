package kr.or.ddit.service.match;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.MatchVO;

public interface IMatchService extends Remote {

	public MatchVO selectTrainer(String id) throws RemoteException;

	public List<MatchVO> matchTrainer(MatchVO mc) throws RemoteException;

	public List<MatchVO> getMatchList() throws RemoteException;

	public MatchVO insertMatch(MatchVO vo) throws RemoteException;
}
