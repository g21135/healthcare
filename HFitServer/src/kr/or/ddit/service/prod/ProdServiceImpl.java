package kr.or.ddit.service.prod;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.prod.IProdDao;
import kr.or.ddit.dao.prod.ProdDaoImpl;
import kr.or.ddit.vo.ProdVO;



public class ProdServiceImpl extends UnicastRemoteObject implements IProdService {
	
	private IProdDao ProdDao;	//사용 할 dao 멤버변수 선언
	private static IProdService service; //싱글톤패턴
	
	protected ProdServiceImpl() throws RemoteException {
		ProdDao = ProdDaoImpl.getInstance();
	}
	
	public static IProdService getInstance() throws RemoteException{
		if(service == null) {
			service = new ProdServiceImpl();
		}
		return service;
	}

	@Override
	public List<ProdVO> ProdList() throws RemoteException {
		return ProdDao.ProdList();
	}

	@Override
	public List<ProdVO> ProdSelectList(int prod_no) throws RemoteException {
		return ProdDao.ProdSelectList(prod_no);
	}

	@Override
	public List<ProdVO> HealthProdList() throws RemoteException {
		
		return ProdDao.HealthProdList();
	}

	@Override
	public List<ProdVO> FoodProdList() throws RemoteException {
		
		return ProdDao.FoodProdList();
	}

	@Override
	public List<ProdVO> ProdMagerment() throws RemoteException {
		return ProdDao.ProdMagerment();
	}

	@Override
	public int InsertProd(ProdVO pv) throws RemoteException {
		return ProdDao.InsertProd(pv);
	}

	@Override
	public int InsertRemain() throws RemoteException {
		return ProdDao.InsertRemain();
	}

	@Override
	public List<ProdVO> PayMagerment() throws RemoteException {
		return ProdDao.PayMagerment();
	}

	@Override
	public int DeleteProd() throws RemoteException {
		return ProdDao.DeleteProd();
	}

	@Override
	public int ProdSelectUpdate(ProdVO pv) throws RemoteException {
		return ProdDao.ProdSelectUpdate(pv);
	}

	@Override
	public int ProdCheckUpdate(ProdVO pv) throws RemoteException {
		return ProdDao.ProdCheckUpdate(pv);
	}
	

}
