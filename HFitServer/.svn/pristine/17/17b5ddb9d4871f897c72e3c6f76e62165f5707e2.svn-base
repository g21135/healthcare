package kr.or.ddit.dao.healthInfo;

import com.ibatis.sqlmap.client.SqlMapClient;
import kr.or.ddit.util.SqlMapClientFactory;

public class HealthInfoDaoImpl implements IhealthInfoDao{
	private static IhealthInfoDao dao;
	private static SqlMapClient smc;
	
	private HealthInfoDaoImpl(){
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IhealthInfoDao getInstance() {
			if(dao == null) {
				dao = new HealthInfoDaoImpl();
			}
			return dao;
	}

}
