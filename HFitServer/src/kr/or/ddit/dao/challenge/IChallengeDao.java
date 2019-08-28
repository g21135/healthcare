package kr.or.ddit.dao.challenge;

import java.util.List;

import kr.or.ddit.vo.ChallengeVO;

public interface IChallengeDao {

	public List<ChallengeVO> selectAll();
	public boolean insert(ChallengeVO cv);
	public ChallengeVO selectNum(int num);
}
