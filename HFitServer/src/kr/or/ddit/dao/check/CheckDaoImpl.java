package kr.or.ddit.dao.check;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.ChallApplyVO;
import kr.or.ddit.vo.ChallengeVO;
import kr.or.ddit.vo.CheckVO;

public class CheckDaoImpl implements ICheckDao {
	private static ICheckDao dao;
	private static SqlMapClient smc;

	private CheckDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}

	public static ICheckDao getInstance() {
		if (dao == null) {
			dao = new CheckDaoImpl();
		}
		return dao;
	}

	@Override
	public List<CheckVO> selectAll(ChallApplyVO cav) {
		List<CheckVO> checkList = new ArrayList<CheckVO>();
		try {
			checkList = smc.queryForList("check.selectAllCav", cav);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checkList;
	}

	@Override
	public boolean insert(CheckVO cv) {
		Object c = 0;
		boolean isExist = false;
		try {
			c = smc.insert("check.insert", cv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(c == null) {
			isExist =true;
		}
		return isExist;
	}

	@Override
	public List<CheckVO> selectAll() {
		List<CheckVO> checkList = new ArrayList<CheckVO>();
		try {
			checkList = smc.queryForList("check.selectAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checkList;
	}

	@Override
	public void update(CheckVO cv) {
		try {
			smc.update("check.updateCheck", cv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CheckVO> selectIdChallNo(ChallApplyVO cav) {
		List<CheckVO> checkList = new ArrayList<CheckVO>();
		try {
			checkList = smc.queryForList("check.selectIdChallNo",cav);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checkList;
	}

}
