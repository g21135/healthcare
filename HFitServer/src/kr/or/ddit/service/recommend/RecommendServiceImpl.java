package kr.or.ddit.service.recommend;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.recommend.IRecommendDao;
import kr.or.ddit.dao.recommend.RecommendDaoImpl;
import kr.or.ddit.vo.RecommendVO;

public class RecommendServiceImpl extends UnicastRemoteObject implements IRecommendService{
	
	private IRecommendDao recomDao;
	private static IRecommendService service;
	
	private RecommendServiceImpl() throws RemoteException {
		recomDao = RecommendDaoImpl.getInstance();
	}
	
	public static IRecommendService getInstance() throws RemoteException{
		if(service == null) {
			service = new RecommendServiceImpl();
		}
		return service;
	}

	@Override
	public int insertRecomDiet(RecommendVO rv) throws RemoteException {
		return recomDao.insertRecomDiet(rv);
	}

	@Override
	public int updateRecomDiet(RecommendVO rv) throws RemoteException {
		return recomDao.updateRecomDiet(rv);
	}

	@Override
	public int deleteRecomDiet(String divcode) throws RemoteException {
		return recomDao.deleteRecomDiet(divcode);
	}

	@Override
	public List<RecommendVO> getAllRecomDietList(int num) throws RemoteException {
		return recomDao.getAllRecomDietList(num);
	}



	//==========================================================================
/*	
	@Override
	public int insertRecomMove(RecommendVO rv) throws RemoteException {
		return reCom.insertRecomMove(rv);
	}

	@Override
	public int updateRecomMove(RecommendVO rv) throws RemoteException {
		return reCom.updateRecomMove(rv);
	}

	@Override
	public int deleteRecomMove(String divcode) throws RemoteException {
		return reCom.deleteRecomMove(divcode);
	}

	@Override
	public List<RecommendVO> getAllRecomMove() throws RemoteException {
		return reCom.getAllRecomMove();
	}

	@Override
	public List<RecommendVO> getSearchRecomMove(RecommendVO rv) throws RemoteException {
		return reCom.getSearchRecomMove(rv);
	}*/
	
}
