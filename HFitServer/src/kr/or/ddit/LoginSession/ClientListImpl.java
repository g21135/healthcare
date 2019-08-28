package kr.or.ddit.LoginSession;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javafx.collections.FXCollections;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.TrainerVO;

public class ClientListImpl extends UnicastRemoteObject implements IClientList {

	private static IClientList client;

	private ClientListImpl() throws RemoteException {
	}

	public static IClientList getInstance() throws RemoteException {
		if (client == null) {
			try {
				client = new ClientListImpl();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return client;
	}

	public Vector<MemberVO> memberList = new Vector<>();
	public Vector<TrainerVO> trainerList = new Vector<>();
//	public static List<MemberVO> memberList = Collections.synchronizedList(new ArrayList<>());
//	public static List<TrainerVO> trainerList = Collections.synchronizedList(new ArrayList<>());

	@Override
	public void addMemberSession(MemberVO mem) throws RemoteException {
		if(memberList.indexOf(mem) == -1) {
			memberList.add(mem);
		}
	}

	@Override
	public void addTrainerSession(TrainerVO trainer) throws RemoteException {
		if(trainerList.indexOf(trainer) == -1) {
		trainerList.add(trainer);
		}
	}

	@Override
	public List<MemberVO> getMemberSession() throws RemoteException {
		return memberList;
	}

	@Override
	public List<TrainerVO> getTrainerSession() throws RemoteException {
		return trainerList;
	}

	@Override
	public void removeMemberSession(MemberVO mem) throws RemoteException {
		Iterator<MemberVO> iter = memberList.iterator();
		while (iter.hasNext()) {
			MemberVO s = iter.next();
			if (s.getMem_id().equals(mem.getMem_id())) {
				iter.remove();
			}
		}
	}

	@Override
	public synchronized void removeTrainerSession(TrainerVO trainer) throws RemoteException {
		int i = trainerList.indexOf(trainer);
		if (i >= 0) {
			trainerList.removeElementAt(i);
		}
	}

}
