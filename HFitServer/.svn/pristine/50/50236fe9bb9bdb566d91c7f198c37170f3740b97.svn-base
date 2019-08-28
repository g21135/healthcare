package kr.or.ddit.dao.reply;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.PostVO;
import kr.or.ddit.vo.ReplyVO;

public class ReplyDaoImpl implements IReplyDao{
	
	private static IReplyDao dao;
	private static SqlMapClient smc;

	protected ReplyDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IReplyDao getInstance() {
		if(dao == null) {
			dao = new ReplyDaoImpl();
			
		}	
		return dao;
	}

	@Override
	public List<ReplyVO> allReplyList(int num) {
		List<ReplyVO> replyList = new ArrayList<>();
		
		try {
			replyList = smc.queryForList("reply.allReplyList", num);
		} catch (SQLException e) {
			System.out.println("에러네");
			e.printStackTrace();
		}
		return replyList;
	}

	@Override
	public int insertReply(ReplyVO rv) {
		int cnt = 0;
		try {
			Object obj = smc.insert("reply.insertReply", rv);
			if(obj == null) {
				cnt = 1;
			}	
		} catch (SQLException e) {
			System.out.println("insertPost 에러");
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteReply(int num) {
		int cnt = 0;
		try {
			cnt = (int) smc.delete("reply.deletePost", num);
		} catch (SQLException e) {
			System.out.println("deletePost 에러");
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateReply(ReplyVO rv) {
		int cnt = 0;
		try {
			cnt = smc.update("reply.updateReply", rv);
		} catch (SQLException e) {
			System.out.println("updateReply 에러");
			e.printStackTrace();
		}
		return cnt;
	}
	
}
