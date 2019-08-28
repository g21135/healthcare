package kr.or.ddit.dao.video;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.HomeTrainingVO;
import kr.or.ddit.vo.VideoVO;

public class VideoDaoImpl implements IVideoDao{
	private static IVideoDao dao;
	private static SqlMapClient smc;
	
	private VideoDaoImpl(){
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IVideoDao getInstance() {
			if(dao == null) {
				dao = new VideoDaoImpl();
			}
			return dao;
	}

	@Override
	public List<VideoVO> selectAll(int channel) {
		List<VideoVO> list = new ArrayList<>();
		try {
			list = smc.queryForList("video.selectAll", channel);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list ;
	}

}
