package kr.or.ddit.service.kcal;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.kcal.KcalDaoImpl;
import kr.or.ddit.vo.KcalVO;

public class KcalServiceImpl extends UnicastRemoteObject implements IKcalService{
	
	private KcalDaoImpl kcDao;
	private static KcalServiceImpl kcService;
	
	private KcalServiceImpl() throws RemoteException{
		kcDao = KcalDaoImpl.getInstance();
	}
	
	public static KcalServiceImpl getInstance() throws RemoteException {
		if(kcService == null) {
			kcService = new KcalServiceImpl();
		}
		return kcService;
	}

	@Override
	public int insertFoodKcal(KcalVO kv) throws RemoteException {
		return kcDao.insertFoodKcal(kv);
	}

	@Override
	public int updateFoodKcal(KcalVO kv) throws RemoteException {
		return kcDao.updateFoodKcal(kv);
	}

	@Override
	public int deleteFoodKcal(int num) throws RemoteException {
		return kcDao.deleteFoodKcal(num);
	}

	@Override
	public List<KcalVO> getAllFoodKcalList() throws RemoteException {		
		return kcDao.getAllFoodKcalList();
	}

	@Override
	public List<KcalVO> getSearchFoodKcal(KcalVO kv) throws RemoteException {		
		return kcDao.getSearchFoodKcal(kv);
	}
//============================================================================
	
	@Override
	public int insertExerKcal(KcalVO kv) throws RemoteException {
		return kcDao.insertExerKcal(kv);
	}

	@Override
	public int updateExerKcal(KcalVO kv) throws RemoteException {
		return kcDao.updateExerKcal(kv);
	}

	@Override
	public int deleteExerKcal(String divcode) throws RemoteException {
		return kcDao.deleteExerKcal(divcode);
	}

	@Override
	public List<KcalVO> getAllExerKcalList() throws RemoteException {
		return kcDao.getAllExerKcal();
	}

	@Override
	public List<KcalVO> getSearchExerKcal(KcalVO kv) throws RemoteException {
		return kcDao.getSearchExerKcal(kv);
	}
	
}
