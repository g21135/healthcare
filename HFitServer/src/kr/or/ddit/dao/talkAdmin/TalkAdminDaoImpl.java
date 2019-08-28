package kr.or.ddit.dao.talkAdmin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.ChallengeVO;
import kr.or.ddit.vo.TalkAdminVO;

public class TalkAdminDaoImpl implements ITalkAdminDao {
	private static ITalkAdminDao dao;
	private static SqlMapClient smc;

	private TalkAdminDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}

	public static ITalkAdminDao getInstance() {
		if (dao == null) {
			dao = new TalkAdminDaoImpl();
		}
		return dao;
	}

	@Override
	public List<TalkAdminVO> selectNum(int num) {
		List<TalkAdminVO> list = new ArrayList<>();
		try {
			list = smc.queryForList("talkAdmin.selectNum", num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void insert(TalkAdminVO tav) {
		try {
			smc.insert("talkAdmin.insert", tav);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<TalkAdminVO> selectId(String mem_id) {
		List<TalkAdminVO> list = new ArrayList<TalkAdminVO>();
		try {
			list = smc.queryForList("talkAdmin.selectId", mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void delete(TalkAdminVO tav) {
		try {
			smc.delete("talkAdmin.delete", tav);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<TalkAdminVO> selectAll() {
		List<TalkAdminVO> list = new ArrayList<TalkAdminVO>();
		try {
			list = smc.queryForList("talkAdmin.selectAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
