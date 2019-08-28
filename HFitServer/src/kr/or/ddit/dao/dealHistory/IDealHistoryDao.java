package kr.or.ddit.dao.dealHistory;

import java.util.List;

import kr.or.ddit.vo.DealHistoryVO;

public interface IDealHistoryDao {


	int InsertOrder(DealHistoryVO dv);

	public List<DealHistoryVO> AllOrderListget(DealHistoryVO dv);

	int DeleteOrder();

	List<DealHistoryVO> SelectOrderListget(DealHistoryVO dv);

	int PayUpdate();

}
