package kr.or.ddit.dao.reply;

import java.util.List;
import kr.or.ddit.vo.ReplyVO;


public interface IReplyDao {
	
	/**
	 * replyVO테이블의 해당 게시판의 전체 댓글 목록을 가져와 반환하는 메서드 
	 * @param num
	 * @return
	 */
	public List<ReplyVO> allReplyList(int num);
	
	/**
	 * ReplyVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param rv
	 * @return
	 */
	public int insertReply(ReplyVO rv);
	
	/**
	 * 댓글번호를 매개변수로 받아서 그 댓글을 삭제하는 메서드
	 * @param num
	 * @return
	 */
	public int deleteReply(int num);
	
	/**
	 * 하나의 ReplyVO자료를 이용하여 DB를 update하는 메서드
	 * @param rv
	 * @return
	 */
	public int updateReply(ReplyVO rv);
	

}
