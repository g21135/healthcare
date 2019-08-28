package kr.or.ddit.dao.kcal;

import java.util.List;

import kr.or.ddit.vo.KcalVO;

public interface IKcalDao {
	// 음식
	public int insertFoodKcal(KcalVO kv); // 추가
	
	public int updateFoodKcal(KcalVO kv); // 수정
	
	public int deleteFoodKcal(int num); // 삭제
	
	public List<KcalVO> getAllFoodKcalList(); // 조회
	
	public List<KcalVO> getSearchFoodKcal(KcalVO kv); // 검색
	
	//운동
	public int insertExerKcal(KcalVO kv);
	
	public int updateExerKcal(KcalVO kv);
	
	public int deleteExerKcal(String divcode);
	
	public List<KcalVO> getAllExerKcal();
	
	public List<KcalVO> getSearchExerKcal(KcalVO kv);
	
}
