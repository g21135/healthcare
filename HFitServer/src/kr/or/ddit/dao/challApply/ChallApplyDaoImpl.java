package kr.or.ddit.dao.challApply;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.ChallApplyVO;

public class ChallApplyDaoImpl implements IChallApplyDao{
	private static IChallApplyDao dao;
	private static SqlMapClient smc;
	
	private ChallApplyDaoImpl(){
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IChallApplyDao getInstance() {
			if(dao == null) {
				dao = new ChallApplyDaoImpl();
			}
			return dao;
	}

	@Override
	public void insert(ChallApplyVO cav) {
		try {
			smc.insert("challApply.insert", cav);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ChallApplyVO> selectId(String id) {
		List<ChallApplyVO> list = new ArrayList<>(); 
		try {
			list = (List<ChallApplyVO>) smc.queryForList("challApply.selectId", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ChallApplyVO selectCav(ChallApplyVO cav) {
		ChallApplyVO c = null;
		try {
			c = (ChallApplyVO) smc.queryForObject("challApply.selectCav", cav);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public List<ChallApplyVO> selectAll() {
		List<ChallApplyVO> list = new ArrayList<>(); 
		try {
			list = (List<ChallApplyVO>) smc.queryForList("challApply.selectAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
