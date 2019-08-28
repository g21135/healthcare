package kr.or.ddit.dao.kcal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.KcalVO;

public class KcalDaoImpl implements IKcalDao{
	private static SqlMapClient smc;

	//private static IKcalDao dao;
	
	private static KcalDaoImpl dao;
	
	private KcalDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static KcalDaoImpl getInstance() {
		if(dao == null) {
			dao = new KcalDaoImpl();
		}
		return dao;
	}

	// 아래 Object 전부 수정 해야됨
	// 음식 
	@Override // 추가
	public int insertFoodKcal(KcalVO kv) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("kcal.insertFoodKcal",kv); // 선언할 때 파라미터가(kv)있으니 여기서도 값이 필요하다. 
			if(obj == null) {
				cnt = 1;				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return cnt;
	}
	
	@Override // 수정
	public int updateFoodKcal(KcalVO kv) {
		int cnt = 0;
		try {
			cnt = smc.update("kcal.updateFoodKcal",kv);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override // 삭제
	public int deleteFoodKcal(int num) {
		int cnt = 0;
		try {
			cnt = smc.delete("kcal.deleteFoodKcal", num);			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override // 조회
	public List<KcalVO> getAllFoodKcalList() {
		List<KcalVO> kcalList = null;
		try {
			kcalList = smc.queryForList("kcal.getFoodKcalAll"); // 파라미터가 없으니 값이 없어도 된다.
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return kcalList;
	}

	@Override // 검색
	public List<KcalVO> getSearchFoodKcal(KcalVO kv) {
		ArrayList<KcalVO> kcalList = null;
		try {
			kcalList = (ArrayList<KcalVO>) smc.queryForList("kcal.getSearchFoodKcal", kv);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return kcalList;
	}

	//==================================================================================================
	// 운동
	@Override
	public int insertExerKcal(KcalVO kv) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("kcal.insertExerKcal", kv);
			if(obj == null) {
				cnt = 1;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public int updateExerKcal(KcalVO kv) {
		int cnt = 0;
		try {
			cnt = smc.update("kcal.updateExerKcal", kv);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public int deleteExerKcal(String divcode) {
		int cnt = 0;
		try {
			cnt = smc.delete("kcal.deleteExerKcal", divcode);			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public List<KcalVO> getAllExerKcal() {
		ArrayList<KcalVO> kcalList = null;
		try {
			kcalList = (ArrayList<KcalVO>) smc.queryForList("kcal.getAllExerKcal");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return kcalList;
	}
	
	@Override
	public List<KcalVO> getSearchExerKcal(KcalVO kv) {
		ArrayList<KcalVO> kcalList = null;
		try {
			kcalList = (ArrayList<KcalVO>) smc.queryForList("kcal.getSearchExerKcal", kv);
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return kcalList;
	}
		
}
