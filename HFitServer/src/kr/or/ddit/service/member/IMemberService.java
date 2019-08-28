package kr.or.ddit.service.member;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.TrainerVO;

public interface IMemberService extends Remote{

	public MemberVO login(String id, String pass) throws RemoteException;
	
	public List<MemberVO> selectId(MemberVO mv) throws RemoteException;

	public List<MemberVO> getMemberInfo(MemberVO id) throws RemoteException;
	
	public int updateMember(MemberVO mv) throws RemoteException;
	
	public int insertMember(MemberVO mv) throws RemoteException;
	
	public List<MemberVO> searchId(MemberVO mv) throws RemoteException;
	
	public List<MemberVO> searchPass(MemberVO mv) throws RemoteException;
	
	public int updatePass(MemberVO mv) throws RemoteException;
	
	public List<MemberVO> getAllMemberList() throws RemoteException;
	
	public int deleteMember(String mem_no) throws RemoteException;
	
	public List<MemberVO> getMember() throws RemoteException;
	
	public int updateMember(String mem_id) throws RemoteException;
	
	public int updateMemberAdmin(MemberVO vo) throws RemoteException;
}
