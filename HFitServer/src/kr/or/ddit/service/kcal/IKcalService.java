package kr.or.ddit.service.kcal;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.KcalVO;

public interface IKcalService extends Remote {
	// 음식 칼로리
	// 추가
	public int insertFoodKcal(KcalVO kv) throws RemoteException;
	// 수정
	public int updateFoodKcal(KcalVO kv) throws RemoteException;
	// 삭제
	public int deleteFoodKcal(int num) throws RemoteException;
	// 목록
	public List<KcalVO> getAllFoodKcalList() throws RemoteException;
	// 검색
	public List<KcalVO> getSearchFoodKcal(KcalVO kv) throws RemoteException;
	
	
	// 운동 칼로리
	public int insertExerKcal(KcalVO kv) throws RemoteException;
	// 수정
	public int updateExerKcal(KcalVO kv) throws RemoteException;
	// 삭제
	public int deleteExerKcal(String divcode) throws RemoteException;
	// 목록
	public List<KcalVO> getAllExerKcalList() throws RemoteException;
	// 검색
	public List<KcalVO> getSearchExerKcal(KcalVO kv) throws RemoteException;
	
}
