package kr.or.ddit.service.member;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.member.IMemberDao;
import kr.or.ddit.dao.member.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl extends UnicastRemoteObject implements IMemberService {

	private static IMemberService service;
	private IMemberDao memberDao;

	private MemberServiceImpl() throws RemoteException {
		memberDao = MemberDaoImpl.getInstance();
	}

	public static IMemberService getInstance() throws RemoteException {
		if (service == null) {
			service = new MemberServiceImpl();
		}
		return service;
	}

	@Override
	public MemberVO login(String id, String pass) {
		return memberDao.login(id, pass);
	}

	@Override
	public List<MemberVO> getMemberInfo(MemberVO id) throws RemoteException {
		return memberDao.getMemberInfo(id);
	}

	@Override
	public int updateMember(MemberVO mv) throws RemoteException {
		return memberDao.updateMember(mv);
	}

	@Override
	public List<MemberVO> selectId(MemberVO mv) throws RemoteException {
		return memberDao.selectId(mv);
	}


	@Override
	public int insertMember(MemberVO mv) throws RemoteException {
		return memberDao.insertMember(mv);
	}

	@Override
	public List<MemberVO> searchId(MemberVO mv) throws RemoteException {
		return memberDao.searchId(mv);
	}

	@Override
	public List<MemberVO> searchPass(MemberVO mv) throws RemoteException {
		return memberDao.searchPass(mv);
	}

	@Override
	public int updatePass(MemberVO mv) throws RemoteException {
		return memberDao.updatePass(mv);
	}
	
	@Override
	public List<MemberVO> getAllMemberList() throws RemoteException {
		return memberDao.getAllMemberList();
	}

	@Override
	public int deleteMember(String mem_no) throws RemoteException {
		return memberDao.deleteMember(mem_no);
	}

	@Override
	public List<MemberVO> getMember() throws RemoteException {
		return memberDao.getMember();
	}

	@Override
	public int updateMember(String mem_id) throws RemoteException {
		return memberDao.updateMember(mem_id);
	}

	@Override
	public int updateMemberAdmin(MemberVO vo) throws RemoteException {
		return memberDao.updateMemberAdmin(vo);
	}
}
