package kr.or.ddit.service.reply;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.ReplyVO;

public interface IReplyService extends Remote{
	
	/**
	 * DB의 reply테이블의 전체 레코드를 가져와 List에 담아서 반환하는 메서드
	 * @return
	 */
	public List<ReplyVO> allReplyList(int num) throws RemoteException;
	
	/**
	 * ReplyVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param pv
	 * @return
	 */
	public int insertReply(ReplyVO rv) throws RemoteException;
	
	/**
	 * 댓글 번호를 매개변수로 받아서 그 댓글을 삭제하는 메서드
	 * @param num
	 * @return
	 */
	public int deleteReply(int num) throws RemoteException;
	
	/**
	 * 하나의 ReplyVO를 이용하여 DB를 update하는 메서드
	 * @param pv
	 * @return
	 */
	public int updateReply(ReplyVO rv) throws RemoteException;


}
