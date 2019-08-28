package kr.or.ddit.service.trainerTalk;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import kr.or.ddit.service.match.IMatchService;
import kr.or.ddit.service.match.MatchServiceImpl;
import kr.or.ddit.service.member.IMemberService;
import kr.or.ddit.service.member.MemberServiceImpl;
import kr.or.ddit.service.openTalk.IOpenTalkService;
import kr.or.ddit.service.openTalk.OpenTalkServiceImpl;
import kr.or.ddit.service.talkAdmin.ITalkAdminService;
import kr.or.ddit.service.talkAdmin.TalkAdminServiceImpl;
import kr.or.ddit.vo.MatchVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.OpenTalkVO;
import kr.or.ddit.vo.TalkAdminVO;
import kr.or.ddit.vo.TrainerVO;
import kr.or.ddit.vo.User;

@SuppressWarnings("serial")
public class ChatServerImpl extends UnicastRemoteObject implements ChatServer { // Thread역할을 하기위해

	private Map<String, List<Map<User, ChatClient>>> group = new HashMap<String, List<Map<User, ChatClient>>>();

	IMatchService match = MatchServiceImpl.getInstance();
	List<MatchVO> matchList;

	IOpenTalkService openTalk = OpenTalkServiceImpl.getInstance();
	List<OpenTalkVO> openTalkList;

	ITalkAdminService talkAdmin = TalkAdminServiceImpl.getInstance();
	List<TalkAdminVO> talkAdminList;

	IMemberService member = MemberServiceImpl.getInstance();

	private static ChatServer server;

	private ChatServerImpl() throws RemoteException {
		matchList = match.selectAll();
		openTalkList = openTalk.selectAll();
	}

	public static ChatServer getInstance() throws RemoteException {
		if (server == null) {
			try {
				server = new ChatServerImpl();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return server;
	}

	private Map<MemberVO, ChatClient> memberList = Collections.synchronizedMap(new HashMap<>());
	private Map<TrainerVO, ChatClient> trainerList = Collections.synchronizedMap(new HashMap<>());

	@Override
	public Map<String, List<Map<User, ChatClient>>> getGroup() {
		return group;
	}

	@Override
	public void setGroup(Map<String, List<Map<User, ChatClient>>> group) throws RemoteException {
		this.group = group;
	}

	@Override
	public void joinGroup(User user, String groupName, ChatClient client) {
		Map<User, ChatClient> userInfo = new HashMap<>();
		userInfo.put(user, client);
		group.get(groupName).add(userInfo);
		try {
			GroupSay(groupName, ((MemberVO) user).getMem_id() + " 회원님이 접속하셨습니다.");
			GroupSay(groupName, ((TrainerVO) user).getTrainer_id() + " 트레이너님이 접속하셨습니다.");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exception e) {
		}
	}

	@Override
	public void groupExit(String opentalk_name, User user) {
		for (Map<User, ChatClient> map : group.get(opentalk_name)) {
			for (User key : map.keySet()) {
				if (key.equals(user)) {
					map.remove(user);
				}
			}
		}
	}

	@Override
	public synchronized void addMemberSession(MemberVO mem, ChatClient client) throws RemoteException {
		memberList.put(mem, client);
		say(mem.getMem_name() + "님이 접속하셨습니다.");
	}

	@Override
	public synchronized void addTrainerSession(TrainerVO trainer, ChatClient client) throws RemoteException {
		trainerList.put(trainer, client);
		say(trainer.getTrainer_name() + "트레이너님이 접속하셨습니다.");
	}

	@Override
	public Map<MemberVO, ChatClient> getMemberSession(MemberVO trainer) throws RemoteException {
		return memberList;
	}

	@Override
	public Map<TrainerVO, ChatClient> getTrainerSession(TrainerVO trainer) throws RemoteException {
		return trainerList;
	}

	@Override
	public synchronized void memberDisconnect(MemberVO mem) throws RemoteException {

		if (memberList.get(mem) != null) {
			say(mem.getMem_name() + "님이 퇴장하셨습니다.");
			memberList.remove(mem);
			System.out.println("A Client exited! Number of member = " + memberList.size());
		} else {
			System.out.println("No such a member in Server! ");
		}
	}

	@Override
	public synchronized void trainerDisconnect(TrainerVO trainer) throws RemoteException {

		if (trainerList.get(trainer) != null) {
			say(trainer.getTrainer_name() + "님이 퇴장하셨습니다.");
			trainerList.remove(trainer);
			System.out.println("A Client exited! Number of triner = " + trainerList.size());
		} else {
			System.out.println("No such a triner in Server! ");
		}
	}

	@Override
	public synchronized void say(String msg) throws RemoteException {
		for (MemberVO mem : memberList.keySet()) {
			for (MatchVO mv : matchList) {
				if (mem.getMem_id().equals(mv.getMem_id())) {
					((ChatClient) memberList.get(mem)).printMessage(msg);
				}
			}
		}

		for (TrainerVO trainer : trainerList.keySet()) {
			for (MatchVO mv : matchList) {
				if (trainer.getTrainer_id().equals(mv.getTrainer_id())) {
					((ChatClient) trainerList.get(trainer)).printMessage(msg);
				}
			}
		}
	}

	@Override
	public synchronized void GroupSay(String groupName, String msg) throws RemoteException {
		for (Map<User, ChatClient> userMap : group.get(groupName)) {
			for (User user : userMap.keySet()) {
				((ChatClient) userMap.get(user)).printGroupMessage(msg);
			}
		}
	}
}
