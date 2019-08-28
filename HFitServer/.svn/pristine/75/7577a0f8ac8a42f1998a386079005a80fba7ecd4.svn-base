package kr.or.ddit.service.dealHistory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.dealHistory.DealHistoryDaoImpl;
import kr.or.ddit.dao.dealHistory.IDealHistoryDao;
import kr.or.ddit.vo.CartVO;
import kr.or.ddit.vo.DealHistoryVO;


public class DealHistoryServiceImpl extends UnicastRemoteObject implements IDealHistoryService {
	
	private IDealHistoryDao DealHistoryDao;	//사용 할 dao 멤버변수 선언
	private static IDealHistoryService service; //싱글톤패턴
	
	protected DealHistoryServiceImpl() throws RemoteException {
		DealHistoryDao = DealHistoryDaoImpl.getInstance();
	}
	
	public static IDealHistoryService getInstance() throws RemoteException{
		if(service == null) {
			service = new DealHistoryServiceImpl();
		}
		return service;
	}

	@Override
	public int InsertOrder(DealHistoryVO dv) throws RemoteException {
		return DealHistoryDao.InsertOrder(dv);
	}

	@Override
	public List<DealHistoryVO> AllOrderListget(DealHistoryVO dv) throws RemoteException {
		return DealHistoryDao.AllOrderListget(dv);
	}
	
	@Override
	public List<DealHistoryVO> SelectOrderListget(DealHistoryVO dv) throws RemoteException {
		return DealHistoryDao.SelectOrderListget(dv);
	}

	@Override
	public int DeleteOrder() throws RemoteException {
		
		return DealHistoryDao.DeleteOrder();
	}

	@Override
	public int PayUpdate() throws RemoteException {
		return DealHistoryDao.PayUpdate();
	}
	


}
