package kr.or.ddit.dao.match;

import java.sql.SQLException;

import java.util.List;

import java.util.ArrayList;

import com.ibatis.sqlmap.client.SqlMapClient;
import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.MatchVO;

public class MatchDaoImpl implements IMatchDao {
	private static IMatchDao dao;
	private static SqlMapClient smc;

	private MatchDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}

	public static IMatchDao getInstance() {
		if (dao == null) {
			dao = new MatchDaoImpl();
		}
		return dao;
	}

	@Override
	public List<MatchVO> selectAll() {
		List<MatchVO> list = new ArrayList<>();
		try {
			list = smc.queryForList("match.selectAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public MatchVO selectTrainer(String id) {
		MatchVO result = null;
		try {
			result = (MatchVO) smc.queryForObject("match.selectTrainer", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<MatchVO> matchTrainer(MatchVO mc) {
		List<MatchVO> matchlist = null;
		try {
			matchlist = smc.queryForList("match.selectMatch", mc);
		} catch (SQLException e) {
			matchlist = null;
			e.printStackTrace();
		}

		return matchlist;
	}

	@Override
	public List<MatchVO> getMatchList() {
		List<MatchVO> list = new ArrayList<>();

		try {
			list = smc.queryForList("match.getMatchList");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public MatchVO insertMatch(MatchVO vo) {
		MatchVO mvo = new MatchVO();

		try {
			mvo = (MatchVO) smc.insert("match.insertMatch", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mvo;
	}

}
