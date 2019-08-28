package kr.or.ddit.service.recommend;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.RecommendVO;

public interface IRecommendService extends Remote{
	public int insertRecomDiet(RecommendVO rv) throws RemoteException;
	
	public int updateRecomDiet(RecommendVO rv) throws RemoteException;
	
	public int deleteRecomDiet(String divcode) throws RemoteException;
	
	public List<RecommendVO> getAllRecomDietList(int num) throws RemoteException;
	
	//============================================================================
/*	
	public int insertRecomMove(RecommendVO rv) throws RemoteException;
	
	public int updateRecomMove(RecommendVO rv) throws RemoteException;
	
	public int deleteRecomMove(String divcode) throws RemoteException;
	
	public List<RecommendVO> getAllRecomMove() throws RemoteException;
	
	public List<RecommendVO> getSearchRecomMove(RecommendVO rv) throws RemoteException;*/
}
