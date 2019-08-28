package kr.or.ddit.dao.talkAdmin;

import java.util.List;

import kr.or.ddit.vo.TalkAdminVO;

public interface ITalkAdminDao {

	List<TalkAdminVO> selectNum(int num);

	void insert(TalkAdminVO tav);

	List<TalkAdminVO> selectId(String mem_id);

	void delete(TalkAdminVO tav);

	List<TalkAdminVO> selectAll();

}
