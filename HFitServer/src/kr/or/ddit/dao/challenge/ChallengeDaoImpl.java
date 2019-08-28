package kr.or.ddit.dao.challenge;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.ChallengeVO;

public class ChallengeDaoImpl implements IChallengeDao {
	private static IChallengeDao dao;
	private static SqlMapClient smc;

	private ChallengeDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}

	public static IChallengeDao getInstance() {
		if (dao == null) {
			dao = new ChallengeDaoImpl();
		}
		return dao;
	}

	@Override
	public List<ChallengeVO> selectAll() {
		List<ChallengeVO> challList = new ArrayList<ChallengeVO>();
		try {
			challList = smc.queryForList("challenge.selectAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return challList;
	}

	@Override
	public boolean insert(ChallengeVO cv) {
		boolean result = false;
		Object obj = null;
		try {
			obj = smc.insert("challenge.insert", cv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (obj == null) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	@Override
	public ChallengeVO selectNum(int num) {
		ChallengeVO cv = new ChallengeVO();
		try {
			cv = (ChallengeVO) smc.queryForObject("challenge.selectNum",num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cv;
	}

}
