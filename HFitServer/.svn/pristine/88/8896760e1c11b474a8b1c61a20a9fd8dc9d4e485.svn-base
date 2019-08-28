package kr.or.ddit.dao.msgHistory;

import com.ibatis.sqlmap.client.SqlMapClient;
import kr.or.ddit.util.SqlMapClientFactory;

public class MsgHistoryDaoImpl implements IMsgHistoryDao{
	private static IMsgHistoryDao dao;
	private static SqlMapClient smc;
	
	private MsgHistoryDaoImpl(){
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IMsgHistoryDao getInstance() {
			if(dao == null) {
				dao = new MsgHistoryDaoImpl();
			}
			return dao;
	}

}
