package kr.or.ddit.dao.check;

import java.util.List;

import kr.or.ddit.vo.ChallApplyVO;
import kr.or.ddit.vo.CheckVO;

public interface ICheckDao {

	List<CheckVO> selectAll(ChallApplyVO cav);

	boolean insert(CheckVO cv);

	List<CheckVO> selectAll();

	void update(CheckVO cv);

	List<CheckVO> selectIdChallNo(ChallApplyVO cav);

}
