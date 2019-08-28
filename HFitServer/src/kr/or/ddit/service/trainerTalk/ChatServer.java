package kr.or.ddit.service.trainerTalk;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.TrainerVO;
import kr.or.ddit.vo.User;

public interface ChatServer extends Remote {

	public void memberDisconnect(MemberVO mem) throws RemoteException;

	public void trainerDisconnect(TrainerVO trainer) throws RemoteException;

	public void say(String msg) throws RemoteException;

	public void addMemberSession(MemberVO mem, ChatClient client) throws RemoteException;

	public void addTrainerSession(TrainerVO trainer, ChatClient client) throws RemoteException;

	public Map<MemberVO, ChatClient> getMemberSession(MemberVO trainer) throws RemoteException;

	public Map<TrainerVO, ChatClient> getTrainerSession(TrainerVO trainer) throws RemoteException;

	Map<String, List<Map<User, ChatClient>>> getGroup() throws RemoteException;

	void joinGroup(User user, String groupName, ChatClient client) throws RemoteException;


	void GroupSay(String groupName, String msg) throws RemoteException;
	
	public void groupExit(String opentalk_name, User user) throws RemoteException;
	public void setGroup(Map<String, List<Map<User, ChatClient>>> group) throws RemoteException;
}
