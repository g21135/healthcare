package kr.or.ddit.dao.homeTraining;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.HomeTrainingVO;

public class HomeTrainingDaoImpl implements IHomeTrainingDao{
	private static IHomeTrainingDao dao;
	private static SqlMapClient smc;
	
	private HomeTrainingDaoImpl(){
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IHomeTrainingDao getInstance() {
			if(dao == null) {
				dao = new HomeTrainingDaoImpl();
			}
			return dao;
	}

	@Override
	public List<HomeTrainingVO> selectAll() {
		List<HomeTrainingVO> list = new ArrayList<>();
		try {
			list = smc.queryForList("homeTraining.selectAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list ;
	}
	

}
