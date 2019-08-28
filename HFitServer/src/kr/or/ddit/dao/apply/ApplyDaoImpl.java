package kr.or.ddit.dao.apply;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.ApplyVO;
import kr.or.ddit.vo.MemberVO;

	public class ApplyDaoImpl implements IApplyDao{
		
	private static IApplyDao dao;
	private static SqlMapClient smc;
	
	private ApplyDaoImpl(){
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IApplyDao getInstance() {
			if(dao == null) {
				dao = new ApplyDaoImpl();
			}
			return dao;
	}
	
	
	@Override
	public Object insertTrainer(ApplyVO vo) {
		Object obj = null;

		try {
			obj = smc.insert("apply.insert", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public List<ApplyVO> getAllApplyList() {
		List<ApplyVO> list = new ArrayList<>();

		try {
			list = smc.queryForList("apply.getAllApplyList");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int deleteApply(int apply_no) {
		int num = 0;

		try {
			num = smc.delete("apply.deleteApply", apply_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return num;
	}
	
}
