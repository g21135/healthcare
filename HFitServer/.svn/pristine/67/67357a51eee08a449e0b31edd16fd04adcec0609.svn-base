package kr.or.ddit.dao.coupon;

import com.ibatis.sqlmap.client.SqlMapClient;
import kr.or.ddit.util.SqlMapClientFactory;

	public class CouponDaoImpl implements ICouponDao{
	private static ICouponDao dao;
	private static SqlMapClient smc;
	
	private CouponDaoImpl(){
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ICouponDao getInstance() {
			if(dao == null) {
				dao = new CouponDaoImpl();
			}
			return dao;
	}

}
