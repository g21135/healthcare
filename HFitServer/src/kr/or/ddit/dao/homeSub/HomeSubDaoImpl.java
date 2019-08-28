package kr.or.ddit.dao.homeSub;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.HomeSubVO;

public class HomeSubDaoImpl implements IHomeSubDao {
	private static IHomeSubDao dao;
	private static SqlMapClient smc;

	private HomeSubDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}

	public static IHomeSubDao getInstance() {
		if (dao == null) {
			dao = new HomeSubDaoImpl();
		}
		return dao;
	}

	@Override
	public void insert(HomeSubVO hsv) {
		try {
			smc.insert("homeSub.insert", hsv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<HomeSubVO> selectAll() {
		List<HomeSubVO> list = new ArrayList<>();

		try {
			list = smc.queryForList("homeSub.selectAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void delete(HomeSubVO hsv) {
		try {
			smc.delete("homeSub.delete", hsv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
