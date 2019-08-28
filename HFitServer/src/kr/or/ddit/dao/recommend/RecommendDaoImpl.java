package kr.or.ddit.dao.recommend;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.RecommendVO;

public class RecommendDaoImpl implements IRecommendDao{
	private static SqlMapClient smc;
	private static IRecommendDao dao;
	
	protected RecommendDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IRecommendDao getInstance() {
		if(dao == null) {
			dao = new RecommendDaoImpl();
		}		
		return dao;
	}

	// 추천식단
	@Override
	public int insertRecomDiet(RecommendVO rv) {
		int cnt = 0;
		try {
			Object obj = smc.insert("recommend.insertRecomDiet", rv);
			if(obj == null) {
				cnt = 1;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateRecomDiet(RecommendVO rv) {
		int cnt = 0;
		try {
			cnt = smc.update("recommend.updateRecomDiet", rv);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteRecomDiet(String divcode) {
		int cnt = 0;
		try {
			cnt = smc.delete("recommend.deleteRecomDiet", divcode);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<RecommendVO> getAllRecomDietList(int num) {
		List<RecommendVO> recomList = new ArrayList<RecommendVO>();
		try {
			recomList = smc.queryForList("recommend.getAllRecomDietList", num);
		}catch(SQLException e){
			System.out.println("리스트에러네");
			e.printStackTrace();
		}
		return recomList;
	}

	
	/*
	// 추천운동
	@Override
	public int insertRecomMove(RecommendVO rv) {
		int cnt = 0;
		try {
			Object obj = smc.insert("recommendvo.insertRecomMove");
			if(obj == null) {
				cnt = 1;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateRecomMove(RecommendVO rv) {
		int cnt = 0;
		try {
			cnt = smc.update("recommendvo.updateRecomMove");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteRecomMove(String divcode) {
		int cnt = 0;
		try {
			cnt = smc.delete("recommendvo.deleteRecomMove");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<RecommendVO> getAllRecomMove() {
		ArrayList<RecommendVO> reComList = null;
		try {
			reComList = (ArrayList<RecommendVO>) smc.queryForList("recommendvo.getAllRecomMove");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return reComList;
	}

	@Override
	public List<RecommendVO> getSearchRecomMove(RecommendVO rv) {
		ArrayList<RecommendVO> reComList = null;
		try {
			reComList = (ArrayList<RecommendVO>) smc.queryForList("recommendvo.getSearchRecomMove", rv); // ("",rv);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return reComList;
	}*/
	
	
}
