package kr.or.ddit.dao.challApply;

import java.util.List;

import kr.or.ddit.vo.ChallApplyVO;

public interface IChallApplyDao {

	void insert(ChallApplyVO cav);

	List<ChallApplyVO> selectId(String id);

	ChallApplyVO selectCav(ChallApplyVO cav);

	List<ChallApplyVO> selectAll();

}
