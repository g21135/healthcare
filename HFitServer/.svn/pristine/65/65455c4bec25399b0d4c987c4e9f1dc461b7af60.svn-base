package kr.or.ddit.dao.remain;

import com.ibatis.sqlmap.client.SqlMapClient;
import kr.or.ddit.util.SqlMapClientFactory;

public class RemainDaoImpl implements IRemainDao{
	private static IRemainDao dao;
	private static SqlMapClient smc;
	
	private RemainDaoImpl(){
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IRemainDao getInstance() {
			if(dao == null) {
				dao = new RemainDaoImpl();
			}
			return dao;
	}


}
