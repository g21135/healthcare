package kr.or.ddit.dao.hope;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.HopeVO;

public class HopeDaoImpl implements IHopeDao {
	private static IHopeDao dao;
	private static SqlMapClient smc;

	private HopeDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}

	public static IHopeDao getInstance() {
		if (dao == null) {
			dao = new HopeDaoImpl();
		}
		return dao;
	}

	@Override
	public Object insertHope(HopeVO vo) {
		Object obj = null;

		try {
			obj = smc.insert("hope.insert", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public HopeVO getMemberHope(String mem_id) {
		HopeVO vo = new HopeVO();

		try {
			vo = (HopeVO) smc.queryForObject("hope.getMemberHope", mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vo;
	}

}
