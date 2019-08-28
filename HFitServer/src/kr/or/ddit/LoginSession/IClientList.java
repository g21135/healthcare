package kr.or.ddit.LoginSession;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.TrainerVO;

public interface IClientList extends Remote{
	public void addMemberSession(MemberVO mem) throws RemoteException;
	public void removeMemberSession(MemberVO mem) throws RemoteException;

	public void addTrainerSession(TrainerVO trainer) throws RemoteException;
	public void removeTrainerSession(TrainerVO trainer) throws RemoteException;
	
	public List<MemberVO> getMemberSession() throws RemoteException;
	public List<TrainerVO> getTrainerSession() throws RemoteException;
	
	
}
