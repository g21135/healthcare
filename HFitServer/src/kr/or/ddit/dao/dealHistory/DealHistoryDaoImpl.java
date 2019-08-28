package kr.or.ddit.dao.dealHistory;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import kr.or.ddit.util.SqlMapClientFactory;

import kr.or.ddit.vo.DealHistoryVO;

public class DealHistoryDaoImpl implements IDealHistoryDao{
	private static IDealHistoryDao dao;
	private static SqlMapClient smc;
	
	private DealHistoryDaoImpl(){
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IDealHistoryDao getInstance() {
			if(dao == null) {
				dao = new DealHistoryDaoImpl();
			}
			return dao;
	}

	@Override
	public int InsertOrder(DealHistoryVO dv) {
		int cnt = 0;
		try {
			cnt = smc.update("deal.InsertOrder",dv);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<DealHistoryVO> AllOrderListget(DealHistoryVO dv) {
		List<DealHistoryVO> orderlist =null;
		try {
			orderlist = smc.queryForList("deal.AllOrderListGet",dv);
		} catch (SQLException e) {
			orderlist = null;
			e.printStackTrace();
		}
		
		return orderlist;
	}

	@Override
	public int DeleteOrder() {
		int cnt = 0;
		try {
			cnt = smc.update("deal.DeleteOrder");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<DealHistoryVO> SelectOrderListget(DealHistoryVO dv) {
		List<DealHistoryVO> orderlist =null;
		try {
			orderlist = smc.queryForList("deal.SelectOrderListGet",dv);
		} catch (SQLException e) {
			orderlist = null;
			e.printStackTrace();
		}
		
		return orderlist;
}

	@Override
	public int PayUpdate() {
		int cnt = 0;
		try {
			cnt = smc.update("deal.PayUpdate");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
}
