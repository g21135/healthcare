package kr.or.ddit.dao.divcode;

import com.ibatis.sqlmap.client.SqlMapClient;
import kr.or.ddit.util.SqlMapClientFactory;

public class DivcodeDaoImpl implements IDivcodeDao{
	private static IDivcodeDao dao;
	private static SqlMapClient smc;
	
	private DivcodeDaoImpl(){
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IDivcodeDao getInstance() {
			if(dao == null) {
				dao = new DivcodeDaoImpl();
			}
			return dao;
	}

}
