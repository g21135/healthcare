package kr.or.ddit.dao.membership;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.MembershipVO;
import kr.or.ddit.vo.TrainerVO;

public class MembershipDaoImpl implements IMembershipDao {

	private static IMembershipDao dao;
	private static SqlMapClient smc;

	private MembershipDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}

	public static IMembershipDao getInstance() {
		if (dao == null) {
			dao = new MembershipDaoImpl();
		}
		return dao;
	}

	@Override
	public List<TrainerVO> getListTrainer() {
		List<TrainerVO> tList = new ArrayList<>();

		try {
			tList = smc.queryForList("trainer.getList");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tList;
	}

	@Override
	public HashMap<String, Object> insertMemberShip(String mem_id, String time) {
		Object obj = null;
		
		Map<String, Object> map = new HashMap<>();
		map.put("mem_id", mem_id);
		map.put("time", time);

		try {
			obj = smc.insert("membership.insert", map);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (HashMap) obj;
	}
	
	@Override
	public List<MembershipVO> getMembershipList(){
		List<MembershipVO> list = new ArrayList<>();
		
		try {
			list = smc.queryForList("membership.getMembershipList");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
