package kr.or.ddit.dao.openTalk;

import java.util.List;

import kr.or.ddit.vo.OpenTalkVO;

public interface IOpenTalkDao {

	List<OpenTalkVO> selectAll();

}
