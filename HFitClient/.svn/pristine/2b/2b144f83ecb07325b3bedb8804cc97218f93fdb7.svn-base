package kr.or.ddit.service.dealHistory;

import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.CartVO;
import kr.or.ddit.vo.DealHistoryVO;

public interface IDealHistoryService {

	public List<DealHistoryVO> AllOrderListget(DealHistoryVO dv)throws RemoteException;
	
	public List<DealHistoryVO> SelectOrderListget(DealHistoryVO dv)throws RemoteException;
	
	public int InsertOrder(DealHistoryVO dv) throws RemoteException;
	
	public int DeleteOrder() throws RemoteException;
	
	public int PayUpdate() throws RemoteException;
}
