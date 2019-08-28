package kr.or.ddit.dao.member;

import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PostVO;

public interface IMemberDao {

	MemberVO login(String id, String pass);
	
	public List<MemberVO> getMemberInfo(MemberVO id);
	
	public int updateMember(MemberVO mv);

	public List<MemberVO> selectId(MemberVO mv);
	
	public int insertMember(MemberVO mv);
	
	public List<MemberVO> searchId(MemberVO mv);
	
	public List<MemberVO> searchPass(MemberVO mv);
	
	public int updatePass(MemberVO mv);
	
	public List<MemberVO> getAllMemberList();

	public int deleteMember(String mem_no);

	public List<MemberVO> getMember();

	public int updateMember(String mem_id);
	
	public int updateMemberAdmin(MemberVO vo);


}
