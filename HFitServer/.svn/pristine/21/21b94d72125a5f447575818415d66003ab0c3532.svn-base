package kr.or.ddit.dao.openTalk;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.MatchVO;
import kr.or.ddit.vo.OpenTalkVO;

public class OpenTalkDaoImpl implements IOpenTalkDao{
	private static IOpenTalkDao dao;
	private static SqlMapClient smc;
	
	private OpenTalkDaoImpl(){
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IOpenTalkDao getInstance() {
			if(dao == null) {
				dao = new OpenTalkDaoImpl();
			}
			return dao;
	}

	@Override
	public List<OpenTalkVO> selectAll() {
		List<OpenTalkVO> list = new ArrayList<>();
		try {
			list = smc.queryForList("openTalk.selectAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


}
