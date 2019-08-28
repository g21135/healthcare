package kr.or.ddit.dao.trainer;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.TrainerVO;

public class TrainerDaoImpl implements ITrainerDao {

	private static ITrainerDao dao;
	private static SqlMapClient smc;

	private TrainerDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}

	public static ITrainerDao getInstance() {
		if (dao == null) {
			dao = new TrainerDaoImpl();
		}
		return dao;
	}

	@Override
	public TrainerVO login(String id, String pass) {
		TrainerVO trainer = new TrainerVO();

		try {
			trainer.setTrainer_id(id);
			trainer.setTrainer_pass(pass);

			trainer = (TrainerVO) smc.queryForObject("trainer.login", trainer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trainer;
	}

	@Override
	public List<TrainerVO> getAllTrainerList() {
		List<TrainerVO> list = new ArrayList<>();

		try {
			list = smc.queryForList("trainer.getAllTrainerList");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int updateTrainer(TrainerVO vo) {
		int cnt = 0;

		try {
			cnt = smc.update("trainer.updateTrainer", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int insertTrainer(TrainerVO vo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("trainer.insertTrainer", vo);
			if (obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			System.out.println("insertMember 에러");
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public TrainerVO getTrainer(String name) {
		TrainerVO result = new TrainerVO();
		try {
			result = (TrainerVO) smc.queryForObject("trainer.getTrainer", name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
