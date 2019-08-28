package kr.or.ddit.dao.board;

import java.util.List;
import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao{
	
	private static IBoardDao dao;
	private static SqlMapClient smc;

	public BoardDaoImpl(){
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IBoardDao getInstance() {
		if(dao == null) {
			dao = new BoardDaoImpl();
		}	
		return dao;
		
	}

	
}
